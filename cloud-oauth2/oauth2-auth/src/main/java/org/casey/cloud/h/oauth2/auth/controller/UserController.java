package org.casey.cloud.h.oauth2.auth.controller;

import org.casey.cloud.h.common.core.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Author Fu Kai
 * @Description todo
 * @Date 2021/1/23 13:15
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping
    public void user() {
        throw new RuntimeException("运行时异常!");
    }

    @GetMapping("/hello")
    public Result hello() {
        return Result.success(null, "hello!");
    }
}
