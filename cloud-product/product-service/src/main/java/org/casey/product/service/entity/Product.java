package org.casey.product.service.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @ClassName Product
 * @Author Fu Kai
 * @Description Product
 * @Date 2020-12-26 23:31:40
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString(exclude = {""})

@TableName("cloud_product")
@ApiModel(value = "Product对象", description = "")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "产品编号", required = false)
    @TableId(value = "id")
    private String id;

    @ApiModelProperty(value = "产品名字", required = false)
    private String name;

    @ApiModelProperty(value = "产品价格", required = false)
    private BigDecimal price;

    @ApiModelProperty(value = "产品数量", required = false)
    private Integer amount;

}
