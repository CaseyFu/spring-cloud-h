package org.casey.account.service.service.impl;

import org.casey.account.service.entity.Permission;
import org.casey.account.service.mapper.PermissionMapper;
import org.casey.account.service.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
* @ClassName PermissionServiceImpl
* @Author Fu Kai
* @Description PermissionServiceImpl
* @Date 2021-01-04 11:41:14
*/

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    private final PermissionMapper permissionMapper;

    @Autowired
    public PermissionServiceImpl(PermissionMapper permissionMapper){
        this.permissionMapper = permissionMapper;
    }

}
