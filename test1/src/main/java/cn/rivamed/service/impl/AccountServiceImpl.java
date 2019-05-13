package cn.rivamed.service.impl;

import cn.rivamed.dao.AccountDao;
import cn.rivamed.entity.Account;
import cn.rivamed.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

}
