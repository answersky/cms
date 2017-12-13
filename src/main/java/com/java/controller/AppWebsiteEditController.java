package com.java.controller;


import com.java.service.ContentService;
import com.java.service.MenuNavService;
import com.utils.JsonUtils;
import com.xiaowo.Content;
import com.xiaowo.ContentType;
import com.xiaowo.WebsiteMenu;
import com.xiaowo.Zone;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author answer
 *         2017/11/2
 */
@Controller
public class AppWebsiteEditController {
    @Resource
    private MenuNavService menuNavService;
    @Resource
    private ContentService contentService;

    @RequestMapping("/appWebsite")
    public String appWebsite(Model model) {
        List<WebsiteMenu> list = menuNavService.getMenuNav("app");
        model.addAttribute("navs", list);
        List<Content> contents = contentService.findContentByCategory("app");
        model.addAttribute("contents", contents);
        model.addAttribute("client", "app");
        return "website/web_content_set";
    }

    @RequestMapping("/contentList")
    public String contentList(Model model, Integer categoryId, String client) {
        List<Content> contents = contentService.findContentByCategory(categoryId);
        model.addAttribute("contents", contents);
        List<WebsiteMenu> list = menuNavService.getMenuNav(client);
        model.addAttribute("navs", list);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("client", client);
        return "website/content";
    }


    @RequestMapping("/addContent")
    public String ueditor(Model model, Integer categoryId, String client) {
        List<WebsiteMenu> list = menuNavService.getMenuNav(client);
        model.addAttribute("navs", list);
        String menuNav = menuNavService.findMenuNav(categoryId);
        model.addAttribute("menuNav", menuNav);
        List<ContentType> types = contentService.findAllTypes();
        model.addAttribute("types", types);
        List<Zone> zones = contentService.findAllZones();
        model.addAttribute("zones", zones);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("client", client);
        return "website/edit";
    }

    @RequestMapping("/saveContent")
    @ResponseBody
    public String saveContent(Integer categoryId, String title, String shortTitle, String sourceUrl, String tag, Integer zoneId,
                              Integer typeId, Integer picId, String picUrl, String recommend, String remark, String decorateMoney,
                              String decorateTime, String monthRepayment, String content, HttpServletRequest request, String client, String activeTime, String cooperativeBusiness) {
        List<String> tags = new ArrayList<>();
        if (tag != null && tag.contains(",")) {
            String[] arr = tag.split(",");
            tags = Arrays.asList(arr);
        } else if (!"".equals(tag)) {
            tags.add(tag);
        }
        //保存标签
        String tagIds = contentService.saveTags(tags);

        //保存图片
        contentService.savePic(picId, picUrl, remark);

        //保存文章内容
        Integer textId = contentService.saveText(sourceUrl, remark, content);

        String editor = (String) request.getSession().getAttribute("username");

        //保存网站更新的内容
        contentService.saveContet(editor, categoryId, textId, picId, title, shortTitle, tagIds, zoneId, typeId, recommend, decorateMoney, decorateTime, monthRepayment, client, activeTime, cooperativeBusiness);

        return "1";
    }

    /**
     * 查询需要更新的文章的内容
     *
     * @param model
     * @param id
     * @param client
     * @return
     */
    @RequestMapping("updatePage")
    public String updatePage(Model model, Integer id, String client) {
        //根据id查询文章
        Content content = contentService.findContentById(id);
        model.addAttribute("content", content);
        Integer categoryId = content.getCategoryId();
        List<WebsiteMenu> list = menuNavService.getMenuNav(client);
        model.addAttribute("navs", list);
        String menuNav = menuNavService.findMenuNav(categoryId);
        model.addAttribute("menuNav", menuNav);
        List<ContentType> types = contentService.findAllTypes();
        model.addAttribute("types", types);
        List<Zone> zones = contentService.findAllZones();
        model.addAttribute("zones", zones);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("client", client);
        return "website/update";
    }

    /**
     * 更新操作
     * @param id
     * @param textId
     * @param title
     * @param shortTitle
     * @param sourceUrl
     * @param tag
     * @param zoneId
     * @param typeId
     * @param picId
     * @param picUrl
     * @param recommend
     * @param remark
     * @param decorateMoney
     * @param decorateTime
     * @param monthRepayment
     * @param content
     * @param client
     * @return
     */
    @RequestMapping("/updateContent")
    @ResponseBody
    public String updateContent(Integer id, Integer textId, String title, String shortTitle, String sourceUrl, String tag, Integer zoneId,
                                Integer typeId, Integer picId, String picUrl, String recommend, String remark, String decorateMoney,
                                String decorateTime, String monthRepayment, String content, String client, String activeTime, String cooperativeBusiness) {
        List<String> tags = new ArrayList<>();
        if (tag != null && tag.contains(",")) {
            String[] arr = tag.split(",");
            tags = Arrays.asList(arr);
        } else if (!"".equals(tag)) {
            tags.add(tag);
        }

        //更新标签
        String tagIds = contentService.saveTags(tags);
        //更新图片
        contentService.savePic(picId, picUrl, remark);
        //更新文章
        contentService.updateText(textId, sourceUrl, remark, content);

        //更新content
        contentService.updateContent(id, title, shortTitle, zoneId, typeId, picId, tagIds, recommend, decorateMoney, decorateTime, monthRepayment, client, activeTime, cooperativeBusiness);
        return "1";
    }

    @RequestMapping("/deleteContent")
    @ResponseBody
    public String deleteContent(Integer id) {
        contentService.deleteContent(id);
        return "1";
    }

    @RequestMapping("/publishContent")
    @ResponseBody
    public void publishProblem(String ids) {
        List list = JsonUtils.listFormJSONStr(ids);
        contentService.publish(list);
    }
}
