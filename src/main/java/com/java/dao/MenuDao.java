package com.java.dao;

import com.xiaowo.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author answer
 *         2017/10/27
 */
public interface MenuDao {
    /**
     * 新增菜单
     *
     * @param menu
     * @return
     */
    int insertMenu(Menu menu);

    /**
     * 根据id查询菜单
     *
     * @param id
     * @return
     */
    Menu findMenuById(@Param("id") Integer id);

    /**
     * 查询某个父级菜单下的所有子菜单
     *
     * @param parentId
     * @return
     */
    List<Menu> findSubMenuByParentId(@Param("parentId") Integer parentId);

    /**
     * 更新菜单
     *
     * @param menu
     */
    void updateMenu(Menu menu);

    /**
     * 查询所有的一级菜单
     *
     * @return
     */
    List<Menu> findFirstMenus();

    /**
     * 获取所有的有效菜单
     *
     * @return
     */
    List<Menu> findMenus();

    /**
     * 删除菜单
     *
     * @param id
     */
    void deleteMenu(Integer id);
}
