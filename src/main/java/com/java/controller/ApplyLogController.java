package com.java.controller;

import com.java.service.ApplyLogService;
import com.xiaowo.ApplyLog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/12/4
 */
@Controller
public class ApplyLogController {
    @Resource
    private ApplyLogService applyLogService;

    @RequestMapping("/applyLog")
    public String applyLog(Model model) {
        List<ApplyLog> applyLogs = applyLogService.findCount();
        model.addAttribute("applyLogs", applyLogs);
        return "count/applyLog";
    }

    @RequestMapping("/applyLogDetail")
    public String applyLogDetail(Model model, String resource) {
        List<ApplyLog> applyLogs = applyLogService.findApplyLogByResource(resource);
        model.addAttribute("applyLogs", applyLogs);
        return "count/applyLog_detail";
    }
}
