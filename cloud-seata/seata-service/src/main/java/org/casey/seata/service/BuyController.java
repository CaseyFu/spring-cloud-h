package org.casey.seata.service;

// import org.casey.cloudorderservice.entity.Order;
import org.casey.seata.openfeign.AccountOpenFeignService;
import org.casey.seata.openfeign.OrderOpenFeignService;
import org.casey.seata.openfeign.ProductOpenFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName BuyController
 * @Author Fu Kai
 * @Description todo
 * @Date 2020/12/30 10:35
 */

@RestController
public class BuyController {

    private final AccountOpenFeignService accountOpenFeignService;
    private final OrderOpenFeignService orderOpenFeignService;
    private final ProductOpenFeignService productOpenFeignService;

    @Autowired
    public BuyController(AccountOpenFeignService accountOpenFeignService, OrderOpenFeignService orderOpenFeignService, ProductOpenFeignService productOpenFeignService) {
        this.accountOpenFeignService = accountOpenFeignService;
        this.orderOpenFeignService = orderOpenFeignService;
        this.productOpenFeignService = productOpenFeignService;
    }

    // @PostMapping("/buy")
    // public Result buy(@RequestBody Order order) {
    //     orderOpenFeignService.createOrder(order.getAccountId(), order.getAmount());
    //
    //     accountOpenFeignService.decreaseMoney(order.getAccountId(), order.getTotal());
    //
    //     productOpenFeignService.decreaseAmount(order.getProductId(), order.getAmount());
    //
    //     return Result.success(null, "购买成功!");
    // }
}
