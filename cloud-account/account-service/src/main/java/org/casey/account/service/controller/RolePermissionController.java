package org.casey.account.service.controller;

import org.casey.account.service.entity.RolePermission;
import org.casey.account.service.service.RolePermissionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

/**
* @ClassName RolePermissionController
* @Author Fu Kai
* @Description RolePermissionController
* @Date 2021-01-04 11:41:14
*/

@RestController
@RequestMapping("/rolePermission")
@Api(tags = {"RolePermission接口"})
public class RolePermissionController {
    private final RolePermissionService rolePermissionService;

    @Autowired
    public RolePermissionController(RolePermissionService rolePermissionService){
        this.rolePermissionService = rolePermissionService;
    }

    @ApiOperation(value = "value", notes = "注释")
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
    
}
