package org.casey.cloud.h.order.service.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @ClassName Order
 * @Author Fu Kai
 * @Description Order
 * @Date 2020-12-26 23:29:35
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString(exclude = {""})

@TableName("cloud_order")
@ApiModel(value = "Order对象", description = "")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单编号", required = false)
    @TableId(value = "id")
    private String id;

    @ApiModelProperty(value = "账号", required = false)
    private String accountId;

    @ApiModelProperty(value = "产品号", required = false)
    private String productId;

    @ApiModelProperty(value = "数量", required = false)
    private Integer amount;

    @ApiModelProperty(value = "总价", required = false)
    private BigDecimal total;

}
