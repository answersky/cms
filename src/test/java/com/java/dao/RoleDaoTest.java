package com.java.dao;

import com.java.BaseTest;
import com.xiaowo.Role;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/10/31
 */
public class RoleDaoTest extends BaseTest {
    @Resource
    private RoleDao roleDao;

    @Test
    public void insertRole() throws Exception {
        Role role = new Role();
        role.setRole("三级管理员");
        role.setRemark("拥有部分的管理权限");
        roleDao.insertRole(role);
    }

    @Test
    public void findRoles() throws Exception {
        List<Role> roles = roleDao.findRoles();
        System.out.println(roles);
    }

    @Test
    public void findRoleById() throws Exception {
        Role role = roleDao.findRoleById(1);
        System.out.println(role);
    }

    @Test
    public void updateRole() throws Exception {
        Role role = roleDao.findRoleById(2);
        role.setRemark("拥有部分菜单的权限");
        roleDao.updateRole(role);
    }

    @Test
    public void deleteRole() throws Exception {
        roleDao.deleteRole(3);
    }

}