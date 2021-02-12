package org.casey.cloud.h.order.service.controller;



import org.casey.cloud.h.common.core.Result;
import org.casey.cloud.h.order.service.entity.Order;
import org.casey.cloud.h.order.service.service.OrderService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;

/**
 * @ClassName OrderController
 * @Author Fu Kai
 * @Description OrderController
 * @Date 2020-12-26 23:29:35
 */

@RestController
@RequestMapping("/order")
@Api(tags = {"Order接口"})
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ApiOperation(value = "value", notes = "注释")
    @GetMapping("/hello")
    public Result hello() {
        return Result.success(null, "hello");
    }

    @PostMapping
    public Result generateOrder(@RequestBody Order order) {
        return Result.success(orderService.createOrder(order), null);
    }

    @DeleteMapping("/{orderId}")
    public Result delete(@PathVariable String orderId) {
        return Result.success(orderService.removeById(orderId), null);
    }

    @PutMapping
    public Result update(@RequestBody Order order) {
        return Result.success(orderService.updateById(order), null);
    }

    @GetMapping("/{orderId}")
    public Result order(@PathVariable String orderId) {
        return Result.success(orderService.getById(orderId), null);
    }

    @GetMapping("/list")
    public Result list() {
        return Result.success(orderService.list(), null);
    }

}
