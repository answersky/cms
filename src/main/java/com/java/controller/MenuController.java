package com.java.controller;

import com.java.service.MenuService;
import com.xiaowo.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author answer
 *         2017/10/27
 */
@Controller
public class MenuController {
    @Resource
    private MenuService menuService;

    @RequestMapping("/menu")
    public String menuIndex(Model model, HttpServletRequest request) {
        List<Menu> list = menuService.findMenus();
        model.addAttribute("menuList", list);

        List<Menu> pMenus = menuService.findParentMenus();
        model.addAttribute("parentMenus", pMenus);
        return "admin/menu";
    }

    @RequestMapping("/saveMenu")
    @ResponseBody
    public void saveMenu(String name, String url, Integer pid) {
        menuService.saveMenu(pid, name, url);
    }

    @RequestMapping("/deleteMenu")
    @ResponseBody
    public int deleteMenu(Integer id) {
        menuService.deleteMenu(id);
        return 1;
    }

    @RequestMapping("/updateMenu")
    @ResponseBody
    public void updateMenu(Integer id, String name, String url, Integer parentId) {
        menuService.updateMenu(id, parentId, name, url);
    }

    @RequestMapping("/findMenuById")
    @ResponseBody
    public Menu findMenuById(Integer id) {
        Menu menu = menuService.findMenuById(id);
        return menu;
    }

}
