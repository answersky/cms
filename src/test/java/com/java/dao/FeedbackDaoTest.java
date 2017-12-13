package com.java.dao;

import com.java.BaseTest;
import com.xiaowo.Feedback;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/11/10
 */
public class FeedbackDaoTest extends BaseTest {
    @Resource
    private FeedbackDao feedbackDao;

    @Test
    public void insertFeedback() throws Exception {
        Feedback feedback = new Feedback();
        feedback.setContent("还能提供更多的装修样板吗？");
        feedback.setTel("18576435728");
        feedbackDao.insertFeedback(feedback);
    }

    @Test
    public void findFeedbacks() throws Exception {
        List<Feedback> list = feedbackDao.findFeedbacks();
        System.out.println(list);
    }

}