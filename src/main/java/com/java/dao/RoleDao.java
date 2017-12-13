package com.java.dao;


import com.xiaowo.Role;

import java.util.List;

/**
 * @author answer
 *         2017/10/30
 */
public interface RoleDao {

    /**
     * 新增角色
     *
     * @param role
     */
    void insertRole(Role role);

    /**
     * 获取所有的角色
     *
     * @return
     */
    List<Role> findRoles();

    /**
     * 根据id查询角色
     *
     * @param id
     * @return
     */
    Role findRoleById(Integer id);

    /**
     * 修改角色
     *
     * @param role
     */
    void updateRole(Role role);

    /**
     * 删除角色
     *
     * @param id
     */
    void deleteRole(Integer id);
}
