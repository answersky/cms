package com.java.service.impl;

import com.java.dao.FeedbackDao;
import com.java.service.FeedbackService;
import com.xiaowo.Feedback;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/11/10
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Resource
    private FeedbackDao feedbackDao;

    @Override
    public List<Feedback> findFeedbacks() {
        return feedbackDao.findFeedbacks();
    }
}
