package org.casey.account.service.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
* @ClassName Permission
* @Author Fu Kai
* @Description Permission
* @Date 2021-01-04 11:41:14
*/

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString(exclude = {""})

@TableName("cloud_permission")
@ApiModel(value = "Permission对象", description = "权限表	permission表说明	type为0是后端控制的请求权限, 只用看name和path字段		type为1是前端控制的菜单权限, 需要看所有字段	其中, 	- 如果父级下只有一个节点, 那么父级不配title字段	- order字段只作用于父级")
public class Permission implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "权限id", required = false)
    private String id;

    @ApiModelProperty(value = "角色名", required = false)
    private String name;

    @ApiModelProperty(value = "uri路径", required = false)
    private String path;

    @ApiModelProperty(value = "权限类型 0Api 1Menu", required = false)
    private Integer type;

    @ApiModelProperty(value = "父级id", required = false)
    private String parentId;

    @ApiModelProperty(value = "type为1时 菜单父级重定向路径", required = false)
    private String redirect;

    @ApiModelProperty(value = "type为1时  菜单页面标题", required = false)
    private String title;

    @ApiModelProperty(value = "type为1时  菜单图标", required = false)
    private String icon;

    @ApiModelProperty(value = "菜单顺序", required = false)
    private Integer order;

    @ApiModelProperty(value = "可用状态 0不可用 1可用", required = false)
    private Integer enabled;

    @ApiModelProperty(value = "描述", required = false)
    private String description;

    @ApiModelProperty(value = "创建时间", required = false)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间", required = false)
    private LocalDateTime updateTime;

}
