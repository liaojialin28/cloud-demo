package org.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.model.QueryUserInfoPageDTO;
import org.example.model.UserInfoDTO;
import org.example.model.UserInfoVO;

import javax.validation.Valid;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author mangbo
 * @since 2023-05-15
 */
public interface UserInfoService extends IService<UserInfo> {


    UserInfoVO createNewUser(@Valid UserInfoDTO userInfoDTO);


    IPage<UserInfoVO> queryUserPage(QueryUserInfoPageDTO queryUserInfoPageDTO);
}
