package com.java.service;


import com.xiaowo.Problem;

import java.util.List;

/**
 * @author answer
 *         2017/11/1
 */
public interface ProblemService {
    /**
     * 新增问题
     *
     * @param categoryId
     * @param problem
     * @param answer
     * @param recommend
     */
    void saveProblem(Integer categoryId, String problem, String answer, String recommend);

    /**
     * 修改问题
     *
     * @param id
     * @param categoryId
     * @param problem
     * @param answer
     * @param recommend
     */
    void updateProblem(Integer id, Integer categoryId, String problem, String answer, String recommend);

    /**
     * 查询所有的问题
     *
     * @return
     */
    List<Problem> findProblems();

    /**
     * 根据id查询问题
     *
     * @param id
     * @return
     */
    Problem findProblemById(Integer id);

    /**
     * 删除问题（修改状态为0）
     *
     * @param id
     */
    void deleteProblem(Integer id);

    /**
     * 发布
     *
     * @param ids
     */
    void publish(List<Integer> ids);
}
