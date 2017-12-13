package com.java.controller;

import com.java.service.FeedbackService;
import com.xiaowo.Feedback;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/11/10
 */
@Controller
public class FeedbackController {
    @Resource
    private FeedbackService feedbackService;

    @RequestMapping("/feedback")
    public String feedback(Model model) {
        List<Feedback> feedbacks = feedbackService.findFeedbacks();
        model.addAttribute("feedbacks", feedbacks);
        return "website/feedback";
    }

}
