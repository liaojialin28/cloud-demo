package org.example.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author mangbo
 * @since 2023-05-15
 */
@Getter
@Setter
@TableName("s_user_info")
@ApiModel(value = "UserInfo对象", description = "用户")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    @ApiModelProperty("用户编号")
    @TableField("user_number")
    private String userNumber;

    @ApiModelProperty("用户姓名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty("用户手机")
    @TableField("contact_phone")
    private String contactPhone;

    @ApiModelProperty("登录账号")
    @TableField("login_name")
    private String loginName;

    @ApiModelProperty("登录密码")
    @TableField("password")
    private String password;

    @ApiModelProperty("用户照片url")
    @TableField("photo")
    private String photo;

    @ApiModelProperty("上次登录IP")
    @TableField("last_login_ip")
    private String lastLoginIp;

    @ApiModelProperty("上次登录时间")
    @TableField("last_login_time")
    private Date lastLoginTime;

    @ApiModelProperty("创建人")
    @TableField("create_by")
    private String createBy;

    @ApiModelProperty("修改人")
    @TableField("update_by")
    private String updateBy;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty("删除标识 0：未删除 1：已删除;0：未删除 1：已删除")
    @TableField("delete_flag")
    private Boolean deleteFlag;

    @ApiModelProperty("昵称")
    @TableField("nick_name")
    private String nickName;

    @ApiModelProperty("电子邮件")
    @TableField("email")
    private String email;

    @ApiModelProperty("性别 1:男 2:女")
    @TableField("sex")
    private Boolean sex;


}
