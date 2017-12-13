package com.java.dao;

import com.java.BaseTest;
import com.xiaowo.FriendshipLink;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/10/31
 */
public class FriendshipLinkDaoTest extends BaseTest {
    @Resource
    private FriendshipLinkDao friendshipLinkDao;

    @Test
    public void insert() throws Exception {
        FriendshipLink friendshipLink = new FriendshipLink();
        friendshipLink.setWord("深圳房产网");
        friendshipLink.setUrl("http://www.baidu.com");
        friendshipLinkDao.insert(friendshipLink);
    }

    @Test
    public void findFriendshipLinks() throws Exception {
        List<FriendshipLink> links = friendshipLinkDao.findFriendshipLinks();
        System.out.println(links);
    }

    @Test
    public void findFriendshipLinkById() throws Exception {
        FriendshipLink friendshipLink = friendshipLinkDao.findFriendshipLinkById(1);
        System.out.println(friendshipLink);
    }

    @Test
    public void updateFriendshipLink() throws Exception {
        FriendshipLink friendshipLink = friendshipLinkDao.findFriendshipLinkById(1);
        friendshipLink.setUrl("http://www.szhome.com/");
        friendshipLinkDao.updateFriendshipLink(friendshipLink);
    }

}