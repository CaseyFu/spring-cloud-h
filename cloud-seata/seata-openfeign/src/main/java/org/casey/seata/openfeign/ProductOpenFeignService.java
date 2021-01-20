package org.casey.seata.openfeign;

import org.casey.common.core.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @InterfaceName ProductOpenFeignService
 * @Author Fu Kai
 * @Description todo
 * @Date 2020/12/30 9:43
 */

@FeignClient(name = "cloud-product-service")
public interface ProductOpenFeignService {
    @GetMapping("/product/hello")
    Result hello();

    @PutMapping("/product/decrease/amount")
    Result decreaseAmount(@RequestParam("productId") String productId, @RequestParam("amount") Integer amount);
}