package org.casey.cloud.h.account.service.service.impl;

import org.casey.cloud.h.account.service.service.RoleService;
import org.casey.cloud.h.account.service.entity.Role;
import org.casey.cloud.h.account.service.mapper.RoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
* @ClassName RoleServiceImpl
* @Author Fu Kai
* @Description RoleServiceImpl
* @Date 2021-01-04 11:41:14
*/

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    private final RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleMapper roleMapper){
        this.roleMapper = roleMapper;
    }

}
