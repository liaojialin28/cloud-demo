package org.example.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.example.config.PageRequest;

import java.io.Serializable;

/**
* <p>
* 用户
* </p>
*
* @author mangbo
* @since 2023-05-09
*/
@Getter
@Setter
@TableName("s_user_info")
@ApiModel(value = "UserInfo对象", description = "用户")
public class QueryUserInfoPageDTO extends PageRequest implements Serializable {

private static final long serialVersionUID = 1L;

//    @ApiModelProperty("用户编号")
//    private String userNumber;
//
//
//    @ApiModelProperty("用户姓名")
//    private String userName;
//
//    @ApiModelProperty("用户手机")
//    private String contactPhone;
//
//    @ApiModelProperty("登录账号")
//    private String loginName;
//
//    @ApiModelProperty("昵称")
//    private String nickName;
//
//    @ApiModelProperty("电子邮件")
//    private String email;
//
//    @ApiModelProperty("性别 1:男 2:女")
//    private Integer sex;
}
