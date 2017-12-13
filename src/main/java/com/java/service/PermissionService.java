package com.java.service;


import com.xiaowo.Menu;

import java.util.List;
import java.util.Map;

/**
 * @author answer
 *         2017/10/27
 */
public interface PermissionService {

    /**
     * 查询用户下的菜单权限
     *
     * @param userId
     * @return
     */
    List<Menu> findMenuByUserId(Integer userId);

    /**
     * 分配菜单权限
     *
     * @param roleId
     * @param menuId
     * @return
     */
    int savePermission(Integer roleId, Integer menuId);

    /**
     * 删除菜单权限
     *
     * @param roleId
     * @param menuId
     * @return
     */
    int deletePermission(Integer roleId, Integer menuId);

    /**
     * 查询角色下的菜单权限
     *
     * @param roleId
     * @return
     */
    List<Map<String, Object>> findTreeByRoleId(Integer roleId);
}
