package org.casey.cloud.h.account.service.service.impl;

import org.casey.cloud.h.account.service.entity.Account;
import org.casey.cloud.h.account.service.service.AccountService;
import org.casey.cloud.h.account.service.mapper.AccountMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
* @ClassName AccountServiceImpl
* @Author Fu Kai
* @Description AccountServiceImpl
* @Date 2021-01-04 11:41:14
*/

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {
    private final AccountMapper accountMapper;

    @Autowired
    public AccountServiceImpl(AccountMapper accountMapper){
        this.accountMapper = accountMapper;
    }

}
