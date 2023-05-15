package org.example.service.impl;

import com.obs.services.ObsClient;
import com.obs.services.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.config.ObsFileConfig;
import org.example.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    @Resource
    private ObsFileConfig obsFileConfig;


    /**
    *@Description: 单文件上传
    *@Author: LiaoJiaLin
    *@Date: 2023/5/11 16:17
    **/
    public String fileUpload(MultipartFile uploadFile) {
        ObsClient obsClient = null;
        try {
            //创建实例
            obsClient = obsFileConfig.getInstance();

//            //上传(创建)文件夹
//            PutObjectRequest request1 = new PutObjectRequest(obsFileConfig.getBucket(),"dir/");
//            obsClient.putObject(request1);

            //获取文件信息
            InputStream inputStream = uploadFile.getInputStream();
            String objectKey = uploadFile.getOriginalFilename();

            long available = inputStream.available();
            PutObjectRequest request = new PutObjectRequest(obsFileConfig.getBucket(), objectKey, inputStream);
            ObjectMetadata objectMetadata = new ObjectMetadata();

            objectMetadata.setContentLength(available);
            request.setMetadata(objectMetadata);
            request.setProgressListener(new ProgressListener() {
                @Override
                public void progressChanged(ProgressStatus status) {
                    // 获取上传平均速率
                    log.info("上传平均速率AverageSpeed:" + status.getAverageSpeed());
                    // 获取上传进度百分比
                    log.info("上传进度百分比TransferPercentage:" + status.getTransferPercentage());
                }
            });
            // 每上传9MB数据反馈上传进度
            request.setProgressInterval(9000 * 1024L);

            //设置公共读
            request.setAcl(AccessControlList.REST_CANNED_PUBLIC_READ);
            PutObjectResult putObjectResult = obsClient.putObject(request);
            String url = putObjectResult.getObjectUrl();
            return url;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //销毁实例
            obsFileConfig.destroy(obsClient);
        }
        return null;
    }


    /**
    *@Description: 多文件上传(多线程)
    *@Author: LiaoJiaLin
    *@Date: 2023/5/11 16:17
    **/
    public List<String> fileUploadUseThreadBatch(List<MultipartFile> files) {
        List<String> results = null;
        try {
            // 创建线程池
            ExecutorService executor = Executors.newFixedThreadPool(files.size());

            // 提交上传任务
            List<Future<PutObjectResult>> futures = new ArrayList<>();
            for (MultipartFile file : files) {
                Future<PutObjectResult> future = executor.submit(new FileUploader(obsFileConfig, file));
                futures.add(future);
            }

            // 等待所有任务完成
            results = new ArrayList<>();
            for (Future<PutObjectResult> future : futures) {
                try {
                    results.add(future.get().getObjectUrl());
                } catch (ExecutionException e) {
                    // 处理上传失败的情况
                    log.info("全部文件上传失败");
                }
            }

            // 关闭线程池
            executor.shutdown();
        } catch (Exception e) {
            e.getMessage();
            log.info("文件上传异常：", e.getMessage());
        }
        return results;
    }

    /**
    *@Description: 多文件上传(单线程)
    *@Author: LiaoJiaLin
    *@Date: 2023/5/11 16:17
    **/
    public List<String> fileUploadBatch(List<MultipartFile> files) {
        List<String> results = null;
        ObsClient obsClient = null;
        try {
            obsClient = obsFileConfig.getInstance();


            for (MultipartFile file : files) {

                //获取文件信息
                String objectKey = file.getOriginalFilename();

                InputStream inputStream = file.getInputStream();
                long available = inputStream.available();
                PutObjectRequest request = new PutObjectRequest(obsFileConfig.getBucket(), objectKey, inputStream);
                ObjectMetadata objectMetadata = new ObjectMetadata();

                objectMetadata.setContentLength(available);
                request.setMetadata(objectMetadata);

                //设置公共读
                request.setAcl(AccessControlList.REST_CANNED_PUBLIC_READ);
                PutObjectResult putObjectResult = obsClient.putObject(request);
                String url = putObjectResult.getObjectUrl();
                results.add(url);
            }
            return results;
        } catch (Exception e) {
            e.getMessage();
            log.info("文件上传异常：", e.getMessage());
        } finally {
            //销毁实例
            obsFileConfig.destroy(obsClient);
        }
        return results;
    }


    /**
    *@Description: 单文件删除
    *@Author: LiaoJiaLin
    *@Date: 2023/5/11 16:18
    **/
    public DeleteObjectResult fileDelete(String fileName) {
        ObsClient obsClient = null;
        try {
            //创建实例
            obsClient = obsFileConfig.getInstance();

            DeleteObjectResult deleteObjectResult = obsClient.deleteObject(obsFileConfig.getBucket(), fileName);
            return deleteObjectResult;
        } finally {
            //销毁实例
            obsFileConfig.destroy(obsClient);
        }
    }


}
