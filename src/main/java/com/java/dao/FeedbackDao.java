package com.java.dao;


import com.xiaowo.Feedback;

import java.util.List;

/**
 * @author answer
 *         2017/11/10
 */
public interface FeedbackDao {

    /**
     * 新增意见反馈
     *
     * @param feedback
     */
    void insertFeedback(Feedback feedback);

    /**
     * 查询所有的意见反馈
     *
     * @return
     */
    List<Feedback> findFeedbacks();
}
