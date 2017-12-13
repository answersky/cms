package com.java.controller;

import com.java.service.DepartmentService;
import com.xiaowo.Department;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/11/24
 */
@Controller
public class DepartmentController {
    @Resource
    private DepartmentService departmentService;

    @RequestMapping("/department")
    public String department(Model model) {
        List<Department> departments = departmentService.findDepartments();
        model.addAttribute("departments", departments);
        return "baseset/department";
    }

    @RequestMapping("/saveDepartment")
    @ResponseBody
    public int saveDepartment(String department, String remark) {
        return departmentService.saveDepartment(department, remark);
    }


    @RequestMapping("/findDepartmentById")
    @ResponseBody
    public Department findDepartmentById(Integer id) {
        return departmentService.findDepartmentById(id);
    }

    @RequestMapping("/updateDepartment")
    @ResponseBody
    public int updateDepartment(Integer id, String department, String remark) {
        return departmentService.updateDepartment(id, department, remark);
    }

    @RequestMapping("/deleteDepartment")
    @ResponseBody
    public void deleteDepartment(Integer id) {
        departmentService.deleteDepartment(id);
    }

}
