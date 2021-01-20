package org.casey.account.service.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.casey.account.service.service.AccountRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @ClassName AccountRoleController
* @Author Fu Kai
* @Description AccountRoleController
* @Date 2021-01-04 11:41:14
*/

@RestController
@RequestMapping("/accountRole")
@Api(tags = {"AccountRole接口"})
public class AccountRoleController {
    private final AccountRoleService accountRoleService;

    @Autowired
    public AccountRoleController(AccountRoleService accountRoleService){
        this.accountRoleService = accountRoleService;
    }

    @ApiOperation(value = "value", notes = "注释")
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
    
}
