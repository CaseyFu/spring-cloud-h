package org.casey.cloud.h.account.service.controller;

import org.casey.cloud.h.account.service.service.PermissionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

/**
* @ClassName PermissionController
* @Author Fu Kai
* @Description PermissionController
* @Date 2021-01-04 11:41:14
*/

@RestController
@RequestMapping("/permission")
@Api(tags = {"Permission接口"})
public class PermissionController {
    private final PermissionService permissionService;

    @Autowired
    public PermissionController(PermissionService permissionService){
        this.permissionService = permissionService;
    }

    @ApiOperation(value = "value", notes = "注释")
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
    
}
