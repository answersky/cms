package com.java.controller;

import com.java.service.PermissionService;
import com.java.service.RoleService;
import com.utils.JsonUtils;
import com.xiaowo.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author answer
 *         2017/10/31
 *         分配菜单权限
 */
@Controller
public class PermissionController {
    @Resource
    private PermissionService permissionService;
    @Resource
    private RoleService roleService;

    @RequestMapping("/permission")
    public String permission(Model model, Integer roleId) {
        List<Role> roles = roleService.findRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("roleId", roleId);
        return "admin/permission";
    }

    @RequestMapping("/tree")
    @ResponseBody
    public List<Map<String, Object>> tree(Integer roleId) {
        return permissionService.findTreeByRoleId(roleId);
    }

    @RequestMapping("/savePermission")
    @ResponseBody
    public int savePermission(Integer roleId, String menuIds) {
        List list = JsonUtils.listFormJSONStr(menuIds);
        int i = 0;
        for (Object obj : list) {
            Integer menuId = (Integer) obj;
            i = permissionService.savePermission(roleId, menuId);
        }
        return i;
    }

    @RequestMapping("/removePermission")
    @ResponseBody
    public int removePermission(Integer roleId, String menuIds) {
        List list = JsonUtils.listFormJSONStr(menuIds);
        int i = 0;
        for (Object obj : list) {
            Integer menuId = (Integer) obj;
            i = permissionService.deletePermission(roleId, menuId);
        }
        return i;
    }
}
