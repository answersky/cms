package com.java.dao;

import com.xiaowo.FriendshipLink;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author answer
 *         2017/10/31
 */
public interface FriendshipLinkDao {

    /**
     * 新增友情链接
     *
     * @param friendshipLink
     */
    void insert(FriendshipLink friendshipLink);

    /**
     * 获取所有的友情链接
     *
     * @return
     */
    List<FriendshipLink> findFriendshipLinks();

    /**
     * 根据id获取友情链接
     *
     * @param id
     * @return
     */
    FriendshipLink findFriendshipLinkById(Integer id);

    /**
     * 更新友情链接
     *
     * @param friendshipLink
     */
    void updateFriendshipLink(FriendshipLink friendshipLink);

    /**
     * 发布
     *
     * @param ids
     */
    void publish(@Param("ids") List<Integer> ids);
}
