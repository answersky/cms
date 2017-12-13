package com.java.service;


import com.xiaowo.Menu;

import java.util.List;

/**
 * @author answer
 *         2017/10/27
 */
public interface MenuService {
    /**
     * 新增菜单
     *
     * @param parentId
     * @param name
     * @param url
     * @return
     */
    int saveMenu(Integer parentId, String name, String url);

    /**
     * 获取整个菜单树
     *
     * @return
     */
    List<Menu> menuTree();

    /**
     * 获取所有的菜单（有效的）
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

    /**
     * 获取所有的父级菜单
     *
     * @return
     */
    List<Menu> findParentMenus();

    /**
     * 根据id查询菜单
     *
     * @param id
     * @return
     */
    Menu findMenuById(Integer id);

    /**
     * 修改菜单
     *
     * @param id
     * @param parentId
     * @param name
     * @param url
     */
    void updateMenu(Integer id, Integer parentId, String name, String url);
}
