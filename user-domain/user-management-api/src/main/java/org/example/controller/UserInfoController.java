package org.example.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author mangbo
 * @since 2023-05-15
 */
@RestController
@RequestMapping("/user-info")
@Api(tags = "用户管理")
public class UserInfoController {

    @PostMapping("fileUpload")
    @ApiOperation("单文件上传")
    public ResponseEntity<String> fileUpload(@RequestParam("file") MultipartFile file) {
//        String url = fileService.fileUpload(file);
        return ResponseEntity.ok("url");
    }

}
