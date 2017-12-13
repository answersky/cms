package com.java.controller;

import com.java.service.RoleService;
import com.java.service.UserRoleService;
import com.xiaowo.Account;
import com.xiaowo.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author answer
 *         2017/10/31
 */
@Controller
public class RoleController {
    @Resource
    private RoleService roleService;
    @Resource
    private UserRoleService userRoleService;

    @RequestMapping("/roles")
    public String roles(Model model) {
        List<Role> roles = roleService.findRoles();
        model.addAttribute("roles", roles);
        return "admin/role";
    }

    @RequestMapping("/saveRole")
    @ResponseBody
    public void saveRole(String role, String remark) {
        roleService.saveRole(role, remark);
    }

    @RequestMapping("/updateRole")
    @ResponseBody
    public void updateRole(Integer id, String role, String remark) {
        roleService.updateRole(id, role, remark);
    }

    @RequestMapping("/findRoleById")
    @ResponseBody
    public Role findRoleById(Integer id) {
        return roleService.findRoleById(id);
    }

    @RequestMapping("/deleteRole")
    @ResponseBody
    public void deleteRole(Integer id) {
        roleService.deleteRole(id);
    }

    @RequestMapping("/findBindUserData")
    @ResponseBody
    public Map<String, List<Account>> findBindUserData(Integer roleId) {
        Map<String, List<Account>> map = new LinkedHashMap<>();
        List<Account> accountList = userRoleService.findBindUserByRoleId(roleId);
        map.put("bind", accountList);
        List<Account> accounts = userRoleService.findUnBindUser();
        map.put("unbind", accounts);
        return map;
    }

    @RequestMapping("/bindUser")
    @ResponseBody
    public int bindUser(Integer roleId, Integer userId) {
        userRoleService.bindRole(userId, roleId);
        return 1;
    }

    @RequestMapping("/unbindUser")
    @ResponseBody
    public int unbindUser(Integer roleId, Integer userId) {
        userRoleService.unbindRole(userId, roleId);
        return 1;
    }
}

