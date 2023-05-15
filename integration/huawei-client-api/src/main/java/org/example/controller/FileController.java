package org.example.controller;


import com.obs.services.model.DeleteObjectResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.example.service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
@Api(tags = "文件管理")
public class FileController {

    private final FileService fileService;

    @PostMapping("fileUpload")
    @ApiOperation("单文件上传")
    public ResponseEntity<String> fileUpload(@RequestParam("file") MultipartFile file) {
        String url = fileService.fileUpload(file);
        return ResponseEntity.ok(url);
    }


    @PostMapping("fileUploadUseThread:batch")
    @ApiOperation("多文件上传(多线程)")
    public ResponseEntity<List<String>> fileUploadUseThreadBatch(@RequestParam("files") List<MultipartFile> files) {
        List<String> putObjectResults = fileService.fileUploadUseThreadBatch(files);
        return ResponseEntity.ok(putObjectResults);
    }

    @PostMapping("fileUpload:batch")
    @ApiOperation("多文件上传(单线程)")
    public ResponseEntity<List<String>> fileUploadBath(@RequestParam("files") List<MultipartFile> files) {
        List<String> putObjectResults = fileService.fileUploadBatch(files);
        return ResponseEntity.ok(putObjectResults);
    }

    @DeleteMapping("fileDelete")
    @ApiOperation("单文件删除")
    public ResponseEntity<DeleteObjectResult> fileDelete(@RequestParam("fileName") String fileName) {
        DeleteObjectResult deleteObjectResult = fileService.fileDelete(fileName);
        return ResponseEntity.ok(deleteObjectResult);
    }




}
