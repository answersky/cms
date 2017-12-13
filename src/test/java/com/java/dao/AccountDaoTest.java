package com.java.dao;

import com.xiaowo.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * Created by answer on 2017/10/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/application.xml"})
public class AccountDaoTest {
    @Resource
    private AccountDao accountDao;

    @Test
    public void insertAccount() throws Exception {
        Account account = new Account();
        account.setUuid(UUID.randomUUID().toString());
        account.setUsername("answer");
        account.setRealName("刘峰");
        account.setPassword("answer");
        accountDao.insertAccount(account);
        System.out.println("当前id：" + account.getId());
    }

    @Test
    public void findAccountById() {
        Account account = accountDao.findAccountById(4);
        System.out.println(account);
    }

    @Test
    public void findAccountByUsername() {
        Account account = accountDao.findAccountByUsername("answer");
        System.out.println(account);
    }

    @Test
    public void unSubscribeById() {
        accountDao.unSubscribeById(1);
    }

    @Test
    public void updatePasswordById() {
        accountDao.updatePasswordById("234", 1);
    }
}