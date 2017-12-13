package com.java.dao;

import com.xiaowo.Content;
import com.xiaowo.Zone;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author answer
 *         2017/11/3
 */
public interface ContentDao {
    /**
     * 保存更新内容
     *
     * @param content
     */
    void insertContent(Content content);

    /**
     * 查询某个栏目下更新的内容记录
     *
     * @param categoryId
     * @return
     */
    List<Content> findContentByCategoryId(Integer categoryId);

    /**
     * 查询部分记录
     *
     * @return
     */
    List<Content> findContents(@Param("client") String client);

    /**
     * 查询所有的站点
     *
     * @return
     */
    List<Zone> findZones();

    /**
     * 根据id查询内容
     *
     * @param id
     * @return
     */
    Content findContentById(Integer id);

    /**
     * 更新content
     *
     * @param content
     */
    void updateContent(Content content);

    /**
     * 修改content的状态
     *
     * @param id
     * @param status
     */
    void updateContentStatus(@Param("id") Integer id, @Param("status") String status);
}
