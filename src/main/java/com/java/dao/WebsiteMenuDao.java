package com.java.dao;

import com.xiaowo.WebsiteMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author answer
 *         2017/11/2
 */
public interface WebsiteMenuDao {

    /**
     * 查询所有的一级菜单
     *@param client
     * @return
     */
    List<WebsiteMenu> findFirstWebsiteMenu(@Param("client") String client);

    /**
     * 根据id查询菜单
     *
     * @param id
     * @return
     */
    WebsiteMenu findWebsiteMenuById(Integer id);

    /**
     * 查询父级菜单下的子菜单
     *
     * @param parentId
     * @return
     */
    List<WebsiteMenu> findWebsiteMenuByParentId(Integer parentId);
}
