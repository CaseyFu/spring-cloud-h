package org.casey.cloud.h.account.service.service.impl;

import org.casey.cloud.h.account.service.mapper.RolePermissionMapper;
import org.casey.cloud.h.account.service.service.RolePermissionService;
import org.casey.cloud.h.account.service.entity.RolePermission;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
* @ClassName RolePermissionServiceImpl
* @Author Fu Kai
* @Description RolePermissionServiceImpl
* @Date 2021-01-04 11:41:14
*/

@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {
    private final RolePermissionMapper rolePermissionMapper;

    @Autowired
    public RolePermissionServiceImpl(RolePermissionMapper rolePermissionMapper){
        this.rolePermissionMapper = rolePermissionMapper;
    }

}
