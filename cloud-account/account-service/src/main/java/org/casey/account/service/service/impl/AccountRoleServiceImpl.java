package org.casey.account.service.service.impl;

import org.casey.account.service.entity.AccountRole;
import org.casey.account.service.mapper.AccountRoleMapper;
import org.casey.account.service.service.AccountRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
* @ClassName AccountRoleServiceImpl
* @Author Fu Kai
* @Description AccountRoleServiceImpl
* @Date 2021-01-04 11:41:14
*/

@Service
public class AccountRoleServiceImpl extends ServiceImpl<AccountRoleMapper, AccountRole> implements AccountRoleService {
    private final AccountRoleMapper accountRoleMapper;

    @Autowired
    public AccountRoleServiceImpl(AccountRoleMapper accountRoleMapper){
        this.accountRoleMapper = accountRoleMapper;
    }

}
