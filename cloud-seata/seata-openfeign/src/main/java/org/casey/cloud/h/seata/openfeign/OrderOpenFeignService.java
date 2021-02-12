package org.casey.cloud.h.seata.openfeign;

import org.casey.cloud.h.common.core.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @InterfaceName OrderOpenFeignService
 * @Author Fu Kai
 * @Description todo
 * @Date 2020/12/30 9:48
 */

@FeignClient(name = "cloud-order-service")
public interface OrderOpenFeignService {
    @GetMapping("/order/hello")
    Result hello();

    @PutMapping("/order")
    Result createOrder(@RequestParam("accountId") String accountId, @RequestParam("amount") Integer amount);
}
