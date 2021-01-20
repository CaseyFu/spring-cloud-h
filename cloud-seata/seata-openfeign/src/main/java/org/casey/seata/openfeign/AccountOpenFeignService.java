package org.casey.seata.openfeign;

import org.casey.common.core.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @InterfaceName AccountFeignService
 * @Author Fu Kai
 * @Description todo
 * @Date 2020/12/30 9:34
 */

@FeignClient(name = "cloud-account-service")
public interface AccountOpenFeignService {
    @GetMapping("/account/hello")
    Result hello();

    @PutMapping("/account/decrease/money")
    Result decreaseMoney(@RequestParam("accountId") String accountId, @RequestParam("money") BigDecimal money);
}