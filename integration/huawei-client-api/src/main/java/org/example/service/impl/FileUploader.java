package org.example.service.impl;

import com.obs.services.ObsClient;
import com.obs.services.model.ObjectMetadata;
import com.obs.services.model.PutObjectRequest;
import com.obs.services.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.example.config.ObsFileConfig;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.concurrent.Callable;


@Slf4j
public class FileUploader implements Callable<PutObjectResult> {


    @Resource
    private final ObsFileConfig obsFileConfig;

    private final MultipartFile file;

    public FileUploader(ObsFileConfig obsFileConfig, MultipartFile file) {
        this.obsFileConfig = obsFileConfig;
        this.file = file;
    }


    @Override
    public PutObjectResult call() throws Exception {
        try {
            ObsClient obsClient = obsFileConfig.getInstance();

            String objectKey = file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();
            log.info("当前上传文件名称{}",objectKey);
            System.out.println("当前上传文件名称:"+objectKey);
            //获取文件信息
            long available = inputStream.available();
            PutObjectRequest request = new PutObjectRequest(obsFileConfig.getBucket(), objectKey, inputStream);
            ObjectMetadata objectMetadata = new ObjectMetadata();

            objectMetadata.setContentLength(available);
            request.setMetadata(objectMetadata);
            PutObjectResult putObjectResult = obsClient.putObject(request);

            return putObjectResult;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("文件上传失敗{}",e.getMessage());
            System.out.println("文件上传失败:"+e.getMessage());
        }
        return null;
    }
}
