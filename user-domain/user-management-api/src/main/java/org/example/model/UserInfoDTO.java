package org.example.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class UserInfoDTO implements Serializable {

private static final long serialVersionUID = 1L;


    @ApiModelProperty("用户编号")
    @TableField("user_number")
    private String userNumber;


    @ApiModelProperty("用户姓名")
    @TableField("user_name")
    @NotBlank(message = "用户姓名不可为空")
    private String userName;


    @ApiModelProperty("用户手机")
    @TableField("contact_phone")
    @NotBlank(message = "用户手机号不可为空")
    private String contactPhone;


    @ApiModelProperty("登录账号")
    @TableField("login_name")
    @NotBlank(message = "用户手机号不可为空")
    private String loginName;


    @ApiModelProperty("用户照片url")
    @TableField("photo")
    private String photo;


    @ApiModelProperty("昵称")
    @TableField("nick_name")
    private String nickName;


    @ApiModelProperty("电子邮件")
    @TableField("email")
    private String email;


    @ApiModelProperty("性别 1:男 2:女")
    @TableField("sex")
    @NotNull(message = "性别不可为空")
    private Integer sex;

    @ApiModelProperty("登录密码")
    @TableField("password")
    @NotNull(message = "密码不可为空")
    private String password;
}
