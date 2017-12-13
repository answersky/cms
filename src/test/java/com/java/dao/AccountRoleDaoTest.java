package com.java.dao;

import com.java.BaseTest;
import com.xiaowo.AccountRole;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author answer
 *         2017/10/31
 */
public class AccountRoleDaoTest extends BaseTest {
    @Resource
    private AccountRoleDao accountRoleDao;

    @Test
    public void insertAccountRole() throws Exception {
        AccountRole accountRole = new AccountRole();
        accountRole.setRoleId(1);
        accountRole.setUserId(4);
        accountRoleDao.insertAccountRole(accountRole);
    }

    @Test
    public void findAccountRoleByUserId() throws Exception {
        AccountRole accountRole = accountRoleDao.findAccountRoleByUserId(4);
        System.out.println(accountRole);
    }

    @Test
    public void removeAccountRole() throws Exception {
        accountRoleDao.removeAccountRole(1, 4);
    }

}