package com.java.dao;

import com.xiaowo.Problem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author answer
 *         2017/11/1
 */
public interface ProblemDao {

    /**
     * 新增问题
     *
     * @param problem
     */
    void insert(Problem problem);

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
     * 更新问题
     *
     * @param problem
     */
    void updateProblem(Problem problem);

    /**
     * 发布
     *
     * @param ids
     */
    void publish(@Param("ids") List<Integer> ids);
}
