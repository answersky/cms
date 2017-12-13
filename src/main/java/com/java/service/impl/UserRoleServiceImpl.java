package com.java.service.impl;

import com.java.dao.AccountDao;
import com.java.dao.AccountRoleDao;
import com.java.service.UserRoleService;
import com.xiaowo.Account;
import com.xiaowo.AccountRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/10/31
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Resource
    private AccountRoleDao accountRoleDao;
    @Resource
    private AccountDao accountDao;

    @Override
    public AccountRole findAccountRoleByUserId(Integer userId) {
        return accountRoleDao.findAccountRoleByUserId(userId);
    }

    @Override
    public void bindRole(Integer userId, Integer roleId) {
        AccountRole accountRole = new AccountRole();
        accountRole.setUserId(userId);
        accountRole.setRoleId(roleId);
        accountRoleDao.insertAccountRole(accountRole);
    }

    @Override
    public void unbindRole(Integer userId, Integer roleId) {
        accountRoleDao.removeAccountRole(roleId, userId);
    }

    @Override
    public List<Account> findBindUserByRoleId(Integer roleId) {
        return accountDao.findBindUserByRoleId(roleId);
    }

    @Override
    public List<Account> findUnBindUser() {
        return accountDao.findNoBindUser();
    }
}
