package com.java.service;

import com.xiaowo.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author answer
 *         2017/10/27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/application.xml"})
public class AccountServiceTest {
    @Resource
    private AccountService accountService;

    @Test
    public void saveAccount() throws Exception {
        int i = accountService.saveAccount("answer", "刘峰", "answer");
        System.out.println(i);
    }

    @Test
    public void findByName() {
        Account account = accountService.findAccountByName("admin");
        System.out.println(account);
    }

    @Test
    public void loginCheck() {
        String info = accountService.loginCheck(null, "admin1", "admin123");
        System.out.println(info);
    }

}