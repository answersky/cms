package com.java.controller;

import com.java.service.PositionService;
import com.xiaowo.Position;
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
public class PositionController {
    @Resource
    private PositionService positionService;

    @RequestMapping("/position")
    public String position(Model model) {
        List<Position> positions = positionService.findPositions();
        model.addAttribute("positions", positions);
        return "baseset/position";
    }

    @RequestMapping("/savePosition")
    @ResponseBody
    public int savePosition(String position, String remark) {
        return positionService.savePosition(position, remark);
    }


    @RequestMapping("/findPositionById")
    @ResponseBody
    public Position findPositionById(Integer id) {
        return positionService.findPositionById(id);
    }

    @RequestMapping("/updatePosition")
    @ResponseBody
    public int updatePosition(Integer id, String position, String remark) {
        return positionService.updatePosition(id, position, remark);
    }

    @RequestMapping("/deletePosition")
    @ResponseBody
    public void deletePosition(Integer id) {
        positionService.deletePosition(id);
    }

}
