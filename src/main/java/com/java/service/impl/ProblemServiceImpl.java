package com.java.service.impl;

import com.java.dao.ProblemDao;
import com.java.service.ProblemService;
import com.xiaowo.Problem;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/11/1
 */
@Service
public class ProblemServiceImpl implements ProblemService {
    @Resource
    private ProblemDao problemDao;

    @Override
    public void saveProblem(Integer categoryId, String problem, String answer, String recommend) {
        Problem pro = new Problem();
        pro.setCategoryId(categoryId);
        pro.setProblem(problem);
        pro.setAnswer(answer);
        pro.setRecommend(recommend);
        problemDao.insert(pro);
    }

    @Override
    public void updateProblem(Integer id, Integer categoryId, String problem, String answer, String recommend) {
        Problem pro = new Problem();
        pro.setId(id);
        pro.setCategoryId(categoryId);
        pro.setProblem(problem);
        pro.setAnswer(answer);
        pro.setRecommend(recommend);
        problemDao.updateProblem(pro);
    }

    @Override
    public List<Problem> findProblems() {
        return problemDao.findProblems();
    }

    @Override
    public Problem findProblemById(Integer id) {
        return problemDao.findProblemById(id);
    }

    @Override
    public void deleteProblem(Integer id) {
        Problem problem = problemDao.findProblemById(id);
        problem.setStatus("0");
        problemDao.updateProblem(problem);
    }

    @Override
    public void publish(List<Integer> ids) {
        problemDao.publish(ids);
    }
}
