package com.java.service.impl;

import com.java.dao.WebsiteMenuDao;
import com.java.service.MenuNavService;
import com.xiaowo.WebsiteMenu;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author answer
 *         2017/11/2
 */
@Service
public class MenuNavServiceImpl implements MenuNavService {
    @Resource
    private WebsiteMenuDao websiteMenuDao;

    @Override
    public List<WebsiteMenu> getMenuNav(String client) {
        List<WebsiteMenu> list = websiteMenuDao.findFirstWebsiteMenu(client);
        if (list != null && list.size() > 0) {
            formatMenu(list);
        }
        return list;
    }

    @Override
    public String findMenuNav(Integer id) {
        List<String> menuNavs = new ArrayList<>();
        String menuNav = "";
        getMenuNavStr(id, menuNavs);
        if (menuNavs.size() > 0) {
            for (int i = menuNavs.size() - 1; i >= 0; i--) {
                if (i == menuNavs.size() - 1) {
                    menuNav = menuNavs.get(i);
                } else {
                    menuNav = menuNav + ">" + menuNavs.get(i);
                }

            }
        }
        return menuNav;
    }

    private void getMenuNavStr(Integer id, List<String> menuNavs) {
        WebsiteMenu websiteMenu = websiteMenuDao.findWebsiteMenuById(id);
        Integer parentId = websiteMenu.getParentId();
        menuNavs.add(websiteMenu.getName());
        if (parentId != 0) {
            getMenuNavStr(parentId, menuNavs);
        }
    }

    private void formatMenu(List<WebsiteMenu> list) {
        for (WebsiteMenu websiteMenu : list) {
            Integer id = websiteMenu.getId();
            List<WebsiteMenu> websiteMenus = websiteMenuDao.findWebsiteMenuByParentId(id);
            if (websiteMenus != null && websiteMenus.size() > 0) {
                websiteMenu.setChildren(websiteMenus);
                formatMenu(websiteMenus);
            }
        }
    }
}
