package com.java.dao;

import com.java.BaseTest;
import com.xiaowo.Menu;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/10/27
 */

public class MenuDaoTest extends BaseTest {
    @Resource
    private MenuDao menuDao;

    @Test
    public void insertMenu() throws Exception {
        Menu menu = new Menu();
        menu.setParentId(0);
        menu.setName("banner管理");
//        menu.setUrl("");
        menuDao.insertMenu(menu);
        System.out.println(menu.getId());
    }

    @Test
    public void findMenuById() throws Exception {
        Menu menu = menuDao.findMenuById(1);
        System.out.println(menu);
    }

    @Test
    public void findSubMenuByParentId() throws Exception {
        List<Menu> menus = menuDao.findSubMenuByParentId(1);
        System.out.println(menus);
    }

    @Test
    public void updateMenu() throws Exception {
        Menu menu = menuDao.findMenuById(2);
        menu.setParentId(1);
        menuDao.updateMenu(menu);
    }

    @Test
    public void findFirstMenus() {
        List<Menu> list = menuDao.findFirstMenus();
        System.out.println(list);
    }

}