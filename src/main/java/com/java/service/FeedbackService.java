package com.java.service;


import com.xiaowo.Feedback;

import java.util.List;

/**
 * @author answer
 *         2017/11/10
 */
public interface FeedbackService {

    /**
     * 查看所有的意见反馈
     *
     * @return
     */
    List<Feedback> findFeedbacks();
}
