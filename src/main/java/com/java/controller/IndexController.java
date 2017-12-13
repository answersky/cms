package com.java.controller;

import com.java.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author answer
 *         2017/10/27
 */
@Controller
public class IndexController {
    @Resource
    private AccountService accountService;


    @RequestMapping("/index")
    public String index() {
        return "login/login_index";
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public String login(HttpServletRequest request, String username, String password) {
        String info = accountService.loginCheck(request, username, password);
        return info;
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        accountService.removeSessionInfo(request);
        return "redirect:/";
    }


}
