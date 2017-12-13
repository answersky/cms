package com.java.service;


import com.xiaowo.FriendshipLink;

import java.util.List;

/**
 * @author answer
 *         2017/10/31
 */
public interface FriendshipLinkService {
    /**
     * 新增友情链接
     *
     * @param word
     * @param url
     */
    void saveFriendshipLink(String word, String url);

    /**
     * 修改友情链接
     *
     * @param id
     * @param word
     * @param url
     */
    void updateFriendshipLink(Integer id, String word, String url);

    /**
     * 删除友情链接（状态置为无效）
     *
     * @param id
     */
    void deleteFriendshipLink(Integer id);

    /**
     * 查询所有的友情链接
     *
     * @return
     */
    List<FriendshipLink> findFriendshipLinks();

    /**
     * 根据id查询友情链接
     *
     * @param id
     * @return
     */
    FriendshipLink findFriendshipLinkById(Integer id);

    /**
     * 发布
     *
     * @param list
     */
    void publish(List list);
}
