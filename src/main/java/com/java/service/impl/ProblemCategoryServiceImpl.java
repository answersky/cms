package com.java.service.impl;

import com.java.dao.ProblemCategoryDao;
import com.java.service.ProblemCategoryService;
import com.xiaowo.ProblemCategory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/11/1
 */
@Service
public class ProblemCategoryServiceImpl implements ProblemCategoryService {
    @Resource
    private ProblemCategoryDao problemCategoryDao;

    @Override
    public void saveProblemCategory(String category, String descript) {
        ProblemCategory problemCategory = new ProblemCategory();
        problemCategory.setCategory(category);
        problemCategory.setDescript(descript);
        problemCategoryDao.insert(problemCategory);
    }

    @Override
    public void updateProblemCategory(Integer id, String category, String descript) {
        ProblemCategory problemCategory = new ProblemCategory();
        problemCategory.setCategory(category);
        problemCategory.setId(id);
        problemCategory.setDescript(descript);
        problemCategoryDao.updateProblemCategory(problemCategory);
    }

    @Override
    public List<ProblemCategory> findProblemCategorys() {
        return problemCategoryDao.findProblemCategorys();
    }

    @Override
    public ProblemCategory findProblemCategoryById(Integer id) {
        return problemCategoryDao.findProblemCategoryById(id);
    }

    @Override
    public void deleteProblemCategoryById(Integer id) {
        ProblemCategory problemCategory = problemCategoryDao.findProblemCategoryById(id);
        problemCategory.setStatus("0");
        problemCategoryDao.updateProblemCategory(problemCategory);
    }
}
