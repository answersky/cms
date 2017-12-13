package com.java.dao;

import com.xiaowo.MenuPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author answer
 *         2017/10/27
 */
public interface MenuPermissionDao {
    /**
     * 分配权限
     *
     * @param menuPermission
     * @return
     */
    int insertPermission(MenuPermission menuPermission);

    /**
     * 查询某个用户下的所有菜单权限
     *
     * @param roleId
     * @return
     */
    List<MenuPermission> findPermissionByRoleId(@Param("roleId") Integer roleId);

    /**
     * 删除权限
     *
     * @param roleId
     * @param menuId
     * @return
     */
    int removePermission(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);
}
