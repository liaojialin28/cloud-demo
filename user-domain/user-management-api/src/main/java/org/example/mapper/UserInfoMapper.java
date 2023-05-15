package org.example.mapper;

import org.example.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author mangbo
 * @since 2023-05-15
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}
