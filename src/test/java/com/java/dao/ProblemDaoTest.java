package com.java.dao;

import com.java.BaseTest;
import com.xiaowo.Problem;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/11/1
 */
public class ProblemDaoTest extends BaseTest {
    @Resource
    private ProblemDao problemDao;

    @Test
    public void insert() throws Exception {
        Problem problem = new Problem();
        problem.setCategoryId(1);
        problem.setProblem("小窝金服是什么？");
        problem.setAnswer("小窝金服是公司");
        problemDao.insert(problem);
    }

    @Test
    public void findProblems() throws Exception {
        List<Problem> problems = problemDao.findProblems();
        System.out.println(problems);
    }

    @Test
    public void findProblemById() throws Exception {
        Problem problem = problemDao.findProblemById(2);
        System.out.println(problem);
    }

    @Test
    public void updateProblem() throws Exception {
        Problem problem = problemDao.findProblemById(2);
        problem.setProblem("小窝金服的地址在哪里？");
        problem.setAnswer("深圳市南山高新园桑达大厦2A");
        problemDao.updateProblem(problem);
    }

}