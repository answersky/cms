package com.java.controller;

import com.java.service.AccountService;
import com.xiaowo.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/10/30
 */
@Controller
public class AccountController {
    @Resource
    private AccountService accountService;

    @RequestMapping("/accountList")
    public String accountList(Model model) {
        List<Account> accounts = accountService.findAccounts();
        model.addAttribute("accounts", accounts);
        return "admin/account";
    }

    @RequestMapping("/validateUsername")
    @ResponseBody
    public int validateUsername(String username) {
        Account account = accountService.findAccountByName(username);
        if (account == null) {
            return 1;
        }
        return 0;
    }

    @RequestMapping("/registerAccount")
    @ResponseBody
    public void registerAccount(String username, String realName, String password) {
        accountService.saveAccount(username, realName, password);
    }

    @RequestMapping("/findAccountById")
    @ResponseBody
    public Account findAccountById(Integer id) {
        return accountService.findAccountById(id);
    }

    @RequestMapping("/deleteAccount")
    @ResponseBody
    public void deleteAccount(Integer id) {
        accountService.deleteAccount(id);
    }

    @RequestMapping("/updateAccount")
    @ResponseBody
    public void updateAccount(Account account) {
        accountService.updateAccount(account);
    }

    @RequestMapping("/updatePassword")
    @ResponseBody
    public void updatePassword(Integer accountId, String password) {
        accountService.updatePassword(accountId, password);
    }
}
