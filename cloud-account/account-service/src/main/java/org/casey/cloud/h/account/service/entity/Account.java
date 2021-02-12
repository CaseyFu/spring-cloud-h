package org.casey.cloud.h.account.service.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
* @ClassName Account
* @Author Fu Kai
* @Description Account
* @Date 2021-01-04 11:41:14
*/

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString(exclude = {""})

@TableName("cloud_account")
@ApiModel(value = "Account对象", description = "用户表")
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id", required = false)
    private String id;

    @ApiModelProperty(value = "组织id", required = false)
    private String organizationId;

    @ApiModelProperty(value = "登录名", required = false)
    private String loginName;

    @ApiModelProperty(value = "密码", required = false)
    private String password;

    @ApiModelProperty(value = "真实名字", required = false)
    private String name;

    @ApiModelProperty(value = "账户余额", required = false)
    private BigDecimal money;

    @ApiModelProperty(value = "昵称", required = false)
    private String nickname;

    @ApiModelProperty(value = "头像", required = false)
    private String avatar;

    @ApiModelProperty(value = "年龄", required = false)
    private Integer age;

    @ApiModelProperty(value = "性别 0女 1男 2未设置", required = false)
    private Integer sex;

    @ApiModelProperty(value = "手机号", required = false)
    private String mobile;

    @ApiModelProperty(value = "邮箱", required = false)
    private String email;

    @ApiModelProperty(value = "身份证号码", required = false)
    private String identityCard;

    @ApiModelProperty(value = "生日", required = false)
    private LocalDateTime birthday;

    @ApiModelProperty(value = "登录时间", required = false)
    private LocalDateTime loginTime;

    @ApiModelProperty(value = "上次登录时间", required = false)
    private LocalDateTime lastLoginTime;

    @ApiModelProperty(value = "账户锁定状态 0锁定 1未锁定", required = false)
    private Integer accountNonLocked;

    @ApiModelProperty(value = "状态 0不可用 1可用", required = false)
    private Integer enabled;

    @ApiModelProperty(value = "描述", required = false)
    private String description;

    @ApiModelProperty(value = "创建时间", required = false)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间", required = false)
    private LocalDateTime updateTime;

}
