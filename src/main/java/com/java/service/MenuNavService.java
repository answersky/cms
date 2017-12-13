package com.java.service;


import com.xiaowo.WebsiteMenu;

import java.util.List;

/**
 * @author answer
 *         2017/11/2
 */
public interface MenuNavService {

    /**
     * 封装菜单导航数据
     *@param client
     * @return
     */
    List<WebsiteMenu> getMenuNav(String client);

    /**
     * 根据最末级菜单查询当前菜单导航
     *
     * @param id
     * @return
     */
    String findMenuNav(Integer id);
}
