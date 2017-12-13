package com.java.dao;

import com.java.BaseTest;
import com.xiaowo.MenuPermission;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/10/27
 */
public class MenuPermissionDaoTest extends BaseTest {
    @Resource
    private MenuPermissionDao menuPermissionDao;

    @Test
    public void insertPermission() throws Exception {
        MenuPermission menuPermission = new MenuPermission();
        menuPermission.setRoleId(5);
        menuPermission.setMenuId(2);
        int i = menuPermissionDao.insertPermission(menuPermission);
        System.out.println(i);
    }

    @Test
    public void findPermissionByUserId() throws Exception {
        List<MenuPermission> list = menuPermissionDao.findPermissionByRoleId(5);
        System.out.println(list);
    }

    @Test
    public void removePermission() throws Exception {
        int i = menuPermissionDao.removePermission(5, 2);
        System.out.println(i);
    }

}