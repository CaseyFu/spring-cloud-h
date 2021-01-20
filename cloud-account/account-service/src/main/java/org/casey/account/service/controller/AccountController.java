package org.casey.account.service.controller;

import org.casey.account.service.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

/**
* @ClassName AccountController
* @Author Fu Kai
* @Description AccountController
* @Date 2021-01-04 11:41:14
*/

@RestController
@RequestMapping("/account")
@Api(tags = {"Account接口"})
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @ApiOperation(value = "value", notes = "注释")
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
    
}
