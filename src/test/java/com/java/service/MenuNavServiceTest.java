package com.java.service;

import com.java.BaseTest;
import com.xiaowo.WebsiteMenu;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/11/2
 */
public class MenuNavServiceTest extends BaseTest {
    @Resource
    private MenuNavService menuNavService;

    @Test
    public void getMenuNav() throws Exception {
        List<WebsiteMenu> list = menuNavService.getMenuNav("app");
        System.out.println(list);
    }

    @Test
    public void getMenuNavStr() throws Exception {
        String menuNav = menuNavService.findMenuNav(21);
        System.out.println(menuNav);
    }

}