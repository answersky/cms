package com.java.dao;


import com.xiaowo.ProblemCategory;

import java.util.List;

/**
 * @author answer
 *         2017/11/1
 */
public interface ProblemCategoryDao {
    /**
     * 新增问题分类
     *
     * @param problemCategory
     */
    void insert(ProblemCategory problemCategory);

    /**
     * 查询所有的问题分类
     *
     * @return
     */
    List<ProblemCategory> findProblemCategorys();

    /**
     * 根据id查询问题分类
     *
     * @param id
     * @return
     */
    ProblemCategory findProblemCategoryById(Integer id);

    /**
     * 修改问题分类
     *
     * @param problemCategory
     */
    void updateProblemCategory(ProblemCategory problemCategory);
}
