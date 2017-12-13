package com.java.service.impl;

import com.java.dao.MenuDao;
import com.java.dao.MenuPermissionDao;
import com.java.service.MenuService;
import com.java.service.PermissionService;
import com.java.service.UserRoleService;
import com.xiaowo.AccountRole;
import com.xiaowo.Menu;
import com.xiaowo.MenuPermission;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author answer
 *         2017/10/27
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private MenuPermissionDao menuPermissionDao;
    @Resource
    private MenuDao menuDao;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private MenuService menuService;

    @Override
    public List<Menu> findMenuByUserId(Integer userId) {
        List<Menu> menus = new ArrayList<>();
        // 根据用户id查询所属的角色
        AccountRole accountRole = userRoleService.findAccountRoleByUserId(userId);
        if (accountRole == null) {
            return menus;
        }
        Integer roleId = accountRole.getRoleId();
        menus = findMenuByRoleId(roleId);
        return menus;
    }

    private List<Menu> findMenuByRoleId(Integer roleId) {
        List<Menu> menus = new ArrayList<>();
        List<MenuPermission> list = menuPermissionDao.findPermissionByRoleId(roleId);
        if (list != null && list.size() > 0) {
            //根据一级菜单id分组存放菜单
            Map<Integer, List<Menu>> map = new LinkedHashMap<>();
            for (MenuPermission menuPermission : list) {
                Integer menuId = menuPermission.getMenuId();
                // 获取父级菜单（只考虑二层菜单）
                Menu menu = menuDao.findMenuById(menuId);
                Integer parentId = menu.getParentId();
                List<Menu> subMenus = map.get(parentId);
                if (subMenus == null) {
                    subMenus = new ArrayList<>();
                }
                subMenus.add(menu);
                map.put(parentId, subMenus);
            }

            //生成菜单树
            Set<Integer> keys = map.keySet();
            for (Integer key : keys) {
                Menu pMenu = menuDao.findMenuById(key);
                List<Menu> subMenu = map.get(key);
                pMenu.setSubMenu(subMenu);
                menus.add(pMenu);
            }
        }
        return menus;
    }

    @Override
    public int savePermission(Integer roleId, Integer menuId) {
        MenuPermission menuPermission = new MenuPermission();
        menuPermission.setRoleId(roleId);
        menuPermission.setMenuId(menuId);
        return menuPermissionDao.insertPermission(menuPermission);
    }

    @Override
    public int deletePermission(Integer roleId, Integer menuId) {
        return menuPermissionDao.removePermission(roleId, menuId);
    }

    @Override
    public List<Map<String, Object>> findTreeByRoleId(Integer roleId) {
        List<Map<String, Object>> treeList = new ArrayList<>();
        List<MenuPermission> list = menuPermissionDao.findPermissionByRoleId(roleId);
        List<Integer> subMenuIds = new ArrayList<>();
        if (list != null && list.size() > 0) {
            //根据一级菜单id分组存放菜单
            for (MenuPermission menuPermission : list) {
                Integer menuId = menuPermission.getMenuId();
                subMenuIds.add(menuId);
            }
        }

        List<Menu> menus = menuService.menuTree();
        if (menus != null && menus.size() > 0) {
            for (Menu menu : menus) {
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("id", menu.getId());
                map.put("text", menu.getName());
                if (menu.getSubMenu() != null && menu.getSubMenu().size() > 0) {
                    map.put("state", "open");
                    List<Map<String, Object>> subTrees = new ArrayList<>();
                    for (Menu me : menu.getSubMenu()) {
                        Map<String, Object> subMap = new LinkedHashMap<>();
                        subMap.put("id", me.getId());
                        subMap.put("text", me.getName());
                        if (subMenuIds.indexOf(me.getId()) >= 0) {
                            subMap.put("checked", true);
                        }
                        subTrees.add(subMap);
                    }
                    map.put("children", subTrees);
                }
                if (subMenuIds.indexOf(menu.getId()) >= 0) {
                    map.put("checked", true);
                }
                treeList.add(map);
            }
        }
        return treeList;
    }
}
