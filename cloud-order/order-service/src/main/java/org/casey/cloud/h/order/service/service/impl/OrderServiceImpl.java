package org.casey.cloud.h.order.service.service.impl;

// import io.seata.spring.annotation.GlobalTransactional;

import org.casey.cloud.h.order.service.entity.Order;
import org.casey.cloud.h.order.service.mapper.OrderMapper;
import org.casey.cloud.h.order.service.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.casey.cloud.h.common.core.util.UUIDUtil;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @ClassName OrderServiceImpl
 * @Author Fu Kai
 * @Description OrderServiceImpl
 * @Date 2020-12-26 23:29:35
 */

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    private final OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    // @GlobalTransactional(rollbackFor = Exception.class)
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public int createOrder(Order order) {
        // 生成订单
        order.setId(UUIDUtil.generate());
        order.setTotal(order.getTotal().multiply(new BigDecimal(order.getAmount())));
        return orderMapper.insert(order);
    }
}
