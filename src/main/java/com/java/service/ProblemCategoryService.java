package com.java.service;


import com.xiaowo.ProblemCategory;

import java.util.List;

/**
 * @author answer
 *         2017/11/1
 */
public interface ProblemCategoryService {
    /**
     * 新增问题分类
     *
     * @param category
     * @param descript
     */
    void saveProblemCategory(String category, String descript);

    /**
     * 修改问题分类
     *
     * @param id
     * @param category
     * @param descript
     */
    void updateProblemCategory(Integer id, String category, String descript);

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
     * 根据id删除问题分类
     *
     * @param id
     */
    void deleteProblemCategoryById(Integer id);
}
