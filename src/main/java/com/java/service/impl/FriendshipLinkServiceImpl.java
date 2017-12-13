package com.java.service.impl;

import com.java.dao.FriendshipLinkDao;
import com.java.service.FriendshipLinkService;
import com.xiaowo.FriendshipLink;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/10/31
 */
@Service
public class FriendshipLinkServiceImpl implements FriendshipLinkService {
    @Resource
    private FriendshipLinkDao friendshipLinkDao;

    @Override
    public void saveFriendshipLink(String word, String url) {
        FriendshipLink friendshipLink = new FriendshipLink();
        friendshipLink.setWord(word);
        friendshipLink.setUrl(url);
        friendshipLinkDao.insert(friendshipLink);
    }

    @Override
    public void updateFriendshipLink(Integer id, String word, String url) {
        FriendshipLink friendshipLink = friendshipLinkDao.findFriendshipLinkById(id);
        friendshipLink.setWord(word);
        friendshipLink.setUrl(url);
        friendshipLinkDao.updateFriendshipLink(friendshipLink);
    }

    @Override
    public void deleteFriendshipLink(Integer id) {
        FriendshipLink friendshipLink = friendshipLinkDao.findFriendshipLinkById(id);
        friendshipLink.setStatus("0");
        friendshipLinkDao.updateFriendshipLink(friendshipLink);
    }

    @Override
    public List<FriendshipLink> findFriendshipLinks() {
        return friendshipLinkDao.findFriendshipLinks();
    }

    @Override
    public FriendshipLink findFriendshipLinkById(Integer id) {
        return friendshipLinkDao.findFriendshipLinkById(id);
    }

    @Override
    public void publish(List list) {
        friendshipLinkDao.publish(list);
    }
}
