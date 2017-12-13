package com.java.controller;

import com.java.parser.CsgServieImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author answer
 *         2017/11/20
 *         南方电网
 */
@RequestMapping("/csg")
@Controller
public class CsgController {
    @Resource
    private CsgServieImpl csgServie;


    @RequestMapping("/findNumByUsernameAndPhone")
    @ResponseBody
    public String findNumByUsernameAndPhone(String username, String phone) {
        return csgServie.findAddressNumByUsernameAndPhone(username, phone);
    }

    @RequestMapping("/findInfoByNumAndPhone")
    @ResponseBody
    public String findInfoByNumAndPhone(String num, String phone) {
        return csgServie.findInfoByAddressNum(num, phone);
    }
}
