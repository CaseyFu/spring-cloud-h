package org.casey.account.service.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
* @ClassName AccountRole
* @Author Fu Kai
* @Description AccountRole
* @Date 2021-01-04 11:41:14
*/

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString(exclude = {""})

@TableName("cloud_account_role")
@ApiModel(value = "AccountRole对象", description = "账户角色中间表")
public class AccountRole implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "账户角色id", required = false)
    private String id;

    @ApiModelProperty(value = "账户id", required = false)
    private String accountId;

    @ApiModelProperty(value = "角色id", required = false)
    private String roleId;

    @ApiModelProperty(value = "可用状态 0不可用 1可用", required = false)
    private Integer enabled;

    @ApiModelProperty(value = "描述", required = false)
    private String description;

    @ApiModelProperty(value = "创建时间", required = false)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间", required = false)
    private LocalDateTime updateTime;

}
