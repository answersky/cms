package com.java.dao;

import com.java.BaseTest;
import com.xiaowo.ProblemCategory;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/11/1
 */
public class ProblemCategoryDaoTest extends BaseTest {
    @Resource
    private ProblemCategoryDao problemCategoryDao;

    @Test
    public void insert() throws Exception {
        ProblemCategory problemCategory = new ProblemCategory();
        problemCategory.setCategory("小窝家装分期");
        problemCategory.setDescript("小窝家装分期");
        problemCategoryDao.insert(problemCategory);
    }

    @Test
    public void findProblemCategorys() throws Exception {
        List<ProblemCategory> list = problemCategoryDao.findProblemCategorys();
        System.out.println(list);
    }

    @Test
    public void findProblemCategoryById() throws Exception {
        ProblemCategory problemCategory = problemCategoryDao.findProblemCategoryById(1);
        System.out.println(problemCategory);
    }

    @Test
    public void updateProblemCategory() throws Exception {
        ProblemCategory problemCategory = problemCategoryDao.findProblemCategoryById(1);
        problemCategory.setDescript("小窝家装分期分类");
        problemCategoryDao.updateProblemCategory(problemCategory);
    }

}