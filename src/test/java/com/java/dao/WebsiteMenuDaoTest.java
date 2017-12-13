package com.java.dao;

import com.java.BaseTest;
import com.xiaowo.WebsiteMenu;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/11/2
 */
public class WebsiteMenuDaoTest extends BaseTest {
    @Resource
    private WebsiteMenuDao websiteMenuDao;

    @Test
    public void findFirstWebsiteMenu() throws Exception {
        List<WebsiteMenu> list = websiteMenuDao.findFirstWebsiteMenu("app");
        System.out.println(list);
    }

    @Test
    public void findWebsiteMenuById() throws Exception {
        WebsiteMenu websiteMenu = websiteMenuDao.findWebsiteMenuById(1);
        System.out.println(websiteMenu);
    }

}