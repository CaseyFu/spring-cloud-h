package org.casey.order.service.service;

import org.casey.order.service.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @InterfaceName OrderService
* @Author Fu Kai
* @Description OrderService
* @Date 2020-12-26 23:29:35
*/

public interface OrderService extends IService<Order> {
    /**
     * 创建订单
     * @param order
     * @return
     */
    int createOrder(Order order);
}
