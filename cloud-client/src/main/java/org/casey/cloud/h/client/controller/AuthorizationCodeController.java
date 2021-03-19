package org.casey.cloud.h.client.controller;

import org.casey.cloud.h.common.core.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AuthorizationCodeController
 * @Author Fu Kai
 * @Description todo
 * @Date 2/26/2021 3:53 PM
 */

@RestController
public class AuthorizationCodeController {
    @GetMapping("/authorization_code")
    public Result authorizationCode(String code, String state) {
        return Result.success("code: " + code, "state: " + state);
    }
}
