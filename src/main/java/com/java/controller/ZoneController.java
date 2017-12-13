package com.java.controller;

import com.java.service.ZoneService;
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
public class ZoneController {
    @Resource
    private ZoneService zoneService;

    @RequestMapping("/zone")
    public String zone(Model model) {
        List<Zone> zones = zoneService.findZones();
        model.addAttribute("zones", zones);
        return "baseset/zone";
    }

    @RequestMapping("/saveZone")
    @ResponseBody
    public int saveZone(String zone) {
        return zoneService.saveZone(zone);
    }

    @RequestMapping("/deleteZone")
    @ResponseBody
    public void deleteZone(Integer id) {
        zoneService.deleteZone(id);
    }

}
