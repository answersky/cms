package com.java.controller;

import com.java.service.ContentService;
import com.java.service.MenuNavService;
import com.xiaowo.Content;
import com.xiaowo.WebsiteMenu;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/11/10
 */
@Controller
public class PcWebsiteEditController {
    @Resource
    private MenuNavService menuNavService;
    @Resource
    private ContentService contentService;

    @RequestMapping("/website")
    public String website(Model model) {
        List<WebsiteMenu> list = menuNavService.getMenuNav("pc");
        model.addAttribute("navs", list);
        List<Content> contents = contentService.findContentByCategory("pc");
        model.addAttribute("contents", contents);
        model.addAttribute("client", "pc");
        return "website/web_content_set";
    }


}
