package com.java.service.impl;

import com.java.dao.MenuDao;
import com.java.service.MenuService;
import com.xiaowo.Menu;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/10/27
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuDao menuDao;

    @Override
    public int saveMenu(Integer parentId, String name, String url) {
        Menu menu = new Menu();
        menu.setParentId(parentId);
        menu.setName(name);
        menu.setUrl(url);
        return menuDao.insertMenu(menu);
    }

    @Override
    public List<Menu> menuTree() {
        //获取所有的一级菜单
        List<Menu> firstMenus = menuDao.findFirstMenus();
        if (firstMenus != null && firstMenus.size() > 0) {
            for (Menu menu : firstMenus) {
                Integer parentId = menu.getId();
                setMenuTree(parentId, menu);
            }
        }
        return firstMenus;
    }

    @Override
    public List<Menu> findMenus() {
        List<Menu> list = menuDao.findMenus();
        if (list != null && list.size() > 0) {
            for (Menu menu : list) {
                Integer pid = menu.getParentId();
                if (pid != null) {
                    Menu m = menuDao.findMenuById(pid);
                    if (m != null) {
                        menu.setpName(m.getName());
                    }
                }
            }
        }
        return list;
    }

    @Override
    public void deleteMenu(Integer id) {
        menuDao.deleteMenu(id);
    }

    @Override
    public List<Menu> findParentMenus() {
        return menuDao.findFirstMenus();
    }

    @Override
    public Menu findMenuById(Integer id) {
        return menuDao.findMenuById(id);
    }

    @Override
    public void updateMenu(Integer id, Integer parentId, String name, String url) {
        Menu menu = new Menu();
        menu.setId(id);
        menu.setName(name);
        menu.setUrl(url);
        menu.setParentId(parentId);
        menuDao.updateMenu(menu);
    }

    private void setMenuTree(Integer parentId, Menu menu) {
        //查询父级下面的所有子菜单
        List<Menu> subMenus = menuDao.findSubMenuByParentId(parentId);
        if (subMenus != null && subMenus.size() > 0) {
            menu.setSubMenu(subMenus);
            for (Menu me : subMenus) {
                Integer pId = me.getId();
                setMenuTree(pId, me);
            }
        }
    }
}
