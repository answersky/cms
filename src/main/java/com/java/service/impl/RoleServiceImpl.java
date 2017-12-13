package com.java.service.impl;

import com.java.dao.RoleDao;
import com.java.service.RoleService;
import com.xiaowo.Role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/10/31
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;


    @Override
    public void saveRole(String role, String remark) {
        Role ro = new Role();
        ro.setRemark(remark);
        ro.setRole(role);
        roleDao.insertRole(ro);
    }

    @Override
    public void updateRole(Integer id, String role, String remark) {
        Role ro = new Role();
        ro.setId(id);
        ro.setRemark(remark);
        ro.setRole(role);
        roleDao.updateRole(ro);
    }

    @Override
    public List<Role> findRoles() {
        return roleDao.findRoles();
    }

    @Override
    public Role findRoleById(Integer id) {
        return roleDao.findRoleById(id);
    }

    @Override
    public void deleteRole(Integer id) {
        roleDao.deleteRole(id);
    }
}
