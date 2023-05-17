package org.example.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.example.model.QueryUserInfoPageDTO;
import org.example.model.UserInfoDTO;
import org.example.model.UserInfoVO;
import org.example.service.UserInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author mangbo
 * @since 2023-05-15
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user-info")
@Api(tags = "用户管理")
public class UserInfoController {

    private final UserInfoService userInfoService;

    @PostMapping(value = "/create")
    @ApiOperation("新增用户")
    public ResponseEntity<UserInfoVO> createNewUser(@Valid @RequestBody UserInfoDTO userInfoDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(userInfoService.createNewUser(userInfoDTO));
    }

    @PostMapping(value = "/search")
    @ApiOperation("查询用户")
    public ResponseEntity<IPage<UserInfoVO>>QueryUserPage(@Valid @RequestBody QueryUserInfoPageDTO queryUserInfoPageDTO){
        return ResponseEntity.ok(userInfoService.queryUserPage(queryUserInfoPageDTO));
    }

}
