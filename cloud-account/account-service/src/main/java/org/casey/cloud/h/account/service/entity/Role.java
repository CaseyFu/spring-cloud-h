package org.casey.cloud.h.account.service.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
* @ClassName Role
* @Author Fu Kai
* @Description Role
* @Date 2021-01-04 11:41:14
*/

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString(exclude = {""})

@TableName("cloud_role")
@ApiModel(value = "Role对象", description = "角色表")
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色id", required = false)
    private String id;

    @ApiModelProperty(value = "角色名", required = false)
    private String name;

    @ApiModelProperty(value = "可用状态 0不可用 1可用", required = false)
    private Integer enabled;

    @ApiModelProperty(value = "描述", required = false)
    private String description;

    @ApiModelProperty(value = "创建时间", required = false)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间", required = false)
    private LocalDateTime updateTime;

}
