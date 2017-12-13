package com.java.controller;

import com.java.service.ProblemCategoryService;
import com.xiaowo.ProblemCategory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/11/1
 */
@Controller
public class ProblemCategoryController {
    @Resource
    private ProblemCategoryService problemCategoryService;

    @RequestMapping("/problemCategory")
    public String problemCategory(Model model) {
        List<ProblemCategory> list = problemCategoryService.findProblemCategorys();
        model.addAttribute("problemCategorys", list);
        return "website/problemCategory";
    }

    @RequestMapping("/saveProblemCategory")
    @ResponseBody
    public void saveProblemCategory(String category, String descript) {
        problemCategoryService.saveProblemCategory(category, descript);
    }

    @RequestMapping("/updateProblemCategory")
    @ResponseBody
    public void updateProblemCategory(Integer id, String category, String descript) {
        problemCategoryService.updateProblemCategory(id, category, descript);
    }

    @RequestMapping("/findProblemCategoryById")
    @ResponseBody
    public ProblemCategory findProblemCategoryById(Integer id) {
        return problemCategoryService.findProblemCategoryById(id);
    }

    @RequestMapping("/deleteProblemCategory")
    @ResponseBody
    public void deleteProblemCategory(Integer id) {
        problemCategoryService.deleteProblemCategoryById(id);
    }

}
