package com.java.controller;

import com.java.service.DepartmentService;
import com.java.service.PositionService;
import com.java.service.RecruitService;
import com.java.service.ZoneService;
import com.utils.JsonUtils;
import com.xiaowo.Department;
import com.xiaowo.Position;
import com.xiaowo.Recruit;
import com.xiaowo.Zone;
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
public class RecruitController {
    @Resource
    private RecruitService recruitService;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private PositionService positionService;
    @Resource
    private ZoneService zoneService;

    @RequestMapping("/recruit")
    public String recruit(Model model) {
        List<Recruit> recruits = recruitService.findAllRecruit();
        model.addAttribute("recruits", recruits);
        List<Department> departments = departmentService.findDepartments();
        model.addAttribute("departments", departments);
        List<Position> positions = positionService.findPositions();
        model.addAttribute("positions", positions);
        List<Zone> zones = zoneService.findZones();
        model.addAttribute("zones", zones);
        return "website/recruit";
    }

    @RequestMapping("/findDetailInfoById")
    @ResponseBody
    public Recruit findDetailInfoById(Integer id) {
        return recruitService.findRecruitById(id);
    }

    @RequestMapping("/saveRecruit")
    @ResponseBody
    public int saveRecruit(Integer departmentId, Integer positionId, Integer zoneId, String title, String duty, String jobRequire, String type) {
        return recruitService.saveRecruit(departmentId, positionId, zoneId, title, duty, jobRequire, type);
    }

    @RequestMapping("/deleteRecruit")
    @ResponseBody
    public void deleteRecruit(Integer id) {
        recruitService.deleteRecruit(id);
    }

    @RequestMapping("/updateRecruit")
    @ResponseBody
    public int updateRecruit(Integer id, Integer departmentId, Integer positionId, Integer zoneId, String title, String duty, String jobRequire, String type) {
        return recruitService.updateRecruit(id, departmentId, positionId, zoneId, title, duty, jobRequire, type);
    }

    @RequestMapping("/findRecruitById")
    @ResponseBody
    public Recruit findRecruitById(Integer id) {
        return recruitService.findRecruitById(id);
    }

    @RequestMapping("/publishRecruit")
    @ResponseBody
    public void publishRecruit(String ids) {
        List list = JsonUtils.listFormJSONStr(ids);
        recruitService.publish(list);
    }

}
