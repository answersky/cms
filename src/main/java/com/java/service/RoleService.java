package com.java.service;


import com.xiaowo.Role;

import java.util.List;

/**
 * @author answer
 *         2017/10/31
 */
public interface RoleService {

    /**
     * 新增角色
     *
     * @param role
     * @param remark
     */
    void saveRole(String role, String remark);

    /**
     * 根据id修改角色
     *
     * @param id
     * @param role
     * @param remark
     */
    void updateRole(Integer id, String role, String remark);

    /**
     * 查询所有的角色
     *
     * @return
     */
    List<Role> findRoles();

    /**
     * 根据id获取角色
     *
     * @param id
     * @return
     */
    Role findRoleById(Integer id);

    /**
     * 删除角色
     *
     * @param id
     */
    void deleteRole(Integer id);
}
