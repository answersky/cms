package com.java.controller;

import com.java.service.FriendshipLinkService;
import com.utils.JsonUtils;
import com.xiaowo.FriendshipLink;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/10/31
 */
@Controller
public class FriendshipLinkController {
    @Resource
    private FriendshipLinkService friendshipLinkService;

    @RequestMapping("/friendshipLinks")
    public String friendshipLinks(Model model) {
        List<FriendshipLink> friendshipLinks = friendshipLinkService.findFriendshipLinks();
        model.addAttribute("friendshipLinks", friendshipLinks);
        return "website/friendshipLinks";
    }

    @RequestMapping("/deleteFriendshipLink")
    @ResponseBody
    public void deleteFriendshipLink(Integer id) {
        friendshipLinkService.deleteFriendshipLink(id);
    }

    @RequestMapping("/findFriendshipLinkById")
    @ResponseBody
    public FriendshipLink findFriendshipLinkById(Integer id) {
        return friendshipLinkService.findFriendshipLinkById(id);
    }

    @RequestMapping("/saveFriendshipLink")
    @ResponseBody
    public void saveFriendshipLink(String word, String url) {
        friendshipLinkService.saveFriendshipLink(word, url);
    }

    @RequestMapping("/updateFriendshipLink")
    @ResponseBody
    public void updateFriendshipLink(Integer id, String word, String url) {
        friendshipLinkService.updateFriendshipLink(id, word, url);
    }

    @RequestMapping("/publishFriendshipLink")
    @ResponseBody
    public void publishFriendshipLink(String ids) {
        List list = JsonUtils.listFormJSONStr(ids);
        friendshipLinkService.publish(list);
    }

}
