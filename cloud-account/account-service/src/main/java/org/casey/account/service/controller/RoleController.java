package org.casey.account.service.controller;

import org.casey.account.service.entity.Role;
import org.casey.account.service.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

/**
* @ClassName RoleController
* @Author Fu Kai
* @Description RoleController
* @Date 2021-01-04 11:41:14
*/

@RestController
@RequestMapping("/role")
@Api(tags = {"Role接口"})
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @ApiOperation(value = "value", notes = "注释")
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
    
}
