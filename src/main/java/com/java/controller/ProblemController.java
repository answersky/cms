package com.java.controller;

import com.java.service.ProblemCategoryService;
import com.java.service.ProblemService;
import com.utils.JsonUtils;
import com.xiaowo.Problem;
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
public class ProblemController {
    @Resource
    private ProblemService problemService;
    @Resource
    private ProblemCategoryService problemCategoryService;

    @RequestMapping("/problem")
    public String problem(Model model) {
        List<Problem> list = problemService.findProblems();
        model.addAttribute("problems", list);
        List<ProblemCategory> problemCategories = problemCategoryService.findProblemCategorys();
        model.addAttribute("problemCategorys", problemCategories);
        return "website/problem";
    }

    @RequestMapping("/saveProblem")
    @ResponseBody
    public void saveProblem(Integer categoryId, String problem, String answer, String recommend) {
        problemService.saveProblem(categoryId, problem, answer, recommend);
    }

    @RequestMapping("/updateProblem")
    @ResponseBody
    public void updateProblem(Integer id, Integer categoryId, String problem, String answer, String recommend) {
        problemService.updateProblem(id, categoryId, problem, answer, recommend);
    }

    @RequestMapping("/findProblemById")
    @ResponseBody
    public Problem findProblemById(Integer id) {
        return problemService.findProblemById(id);
    }

    @RequestMapping("/deleteProblem")
    @ResponseBody
    public void deleteProblem(Integer id) {
        problemService.deleteProblem(id);
    }

    @RequestMapping("/publishProblem")
    @ResponseBody
    public void publishProblem(String ids) {
        List list = JsonUtils.listFormJSONStr(ids);
        problemService.publish(list);
    }
}
