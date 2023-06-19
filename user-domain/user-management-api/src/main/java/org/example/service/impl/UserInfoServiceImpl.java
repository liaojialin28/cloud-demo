package org.example.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.example.entity.UserInfo;
import org.example.mapper.UserInfoMapper;
import org.example.model.QueryUserInfoPageDTO;
import org.example.model.UserInfoDTO;
import org.example.model.UserInfoVO;
import org.example.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.util.servlet.ServletUtils.getClientIP;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author mangbo
 * @since 2023-05-15
 */
@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    private final PasswordEncoder passwordEncoder;

    private final UserInfoMapper userInfoMapper;


    public boolean isPasswordMatch(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }



    /**
     * 对密码进行加密
     *
     * @param password 密码
     * @return 加密后的密码
     */
    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public UserInfoVO createNewUser(UserInfoDTO userInfoDTO) {
        Date date = new Date();
        String userIp = getClientIP();

        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoDTO, userInfo);
        userInfo.setPassword(encodePassword(userInfo.getPassword()));
        userInfo.setCreateTime(date);
        userInfoMapper.insert(userInfo);
        UserInfoVO userInfoVO = new UserInfoVO();
        Long userId = userInfo.getUserId();
        UserInfo userInfo1 = userInfoMapper.selectById(userId);
        BeanUtils.copyProperties(userInfo1, userInfoVO);
        return userInfoVO;
    }

    public IPage<UserInfoVO> queryUserPage(QueryUserInfoPageDTO queryUserInfoPageDTO) {

        Page<UserInfo> page = new Page<>(queryUserInfoPageDTO.getPageNo(), queryUserInfoPageDTO.getPageSize());
        IPage<UserInfo> userInfoPage = userInfoMapper.selectPage(page, null
//                .eq(StringUtils.isNotBlank(queryUserInfoPageDTO.getUserNumber()), UserInfo::getUserNumber, queryUserInfoPageDTO.getUserNumber())
//                .like(StringUtils.isNotBlank(queryUserInfoPageDTO.getUserName()), UserInfo::getUserName, queryUserInfoPageDTO.getUserName())
//                .eq(StringUtils.isNotBlank(queryUserInfoPageDTO.getContactPhone()), UserInfo::getContactPhone, queryUserInfoPageDTO.getContactPhone())
//                .eq(StringUtils.isNotBlank(queryUserInfoPageDTO.getLoginName()), UserInfo::getLoginName, queryUserInfoPageDTO.getLoginName())
//                .like(StringUtils.isNotBlank(queryUserInfoPageDTO.getNickName()), UserInfo::getNickName, queryUserInfoPageDTO.getNickName())
//                .eq(StringUtils.isNotBlank(queryUserInfoPageDTO.getEmail()), UserInfo::getEmail, queryUserInfoPageDTO.getEmail())
//                .eq(queryUserInfoPageDTO.getSex() != null, UserInfo::getSex, queryUserInfoPageDTO.getSex())
        );
        // 将查询结果对象转换为 VO 层对象
        List<UserInfoVO> userVOList = userInfoPage.getRecords().stream().map(user -> {
            UserInfoVO userVO = new UserInfoVO();
            BeanUtils.copyProperties(user, userVO);
            return userVO;
        }).collect(Collectors.toList());

        IPage<UserInfoVO> pageInfo = new Page<>();
        pageInfo.setTotal(userInfoPage.getTotal());
        pageInfo.setRecords(userVOList);
        pageInfo.setPages(userInfoPage.getPages());
        pageInfo.setSize(userInfoPage.getSize());
        return pageInfo;

    }


    public void deleteUser(Long userId){
        userInfoMapper.deleteById(userId);

        List list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        userInfoMapper.deleteBatchIds(list);
    }
}
