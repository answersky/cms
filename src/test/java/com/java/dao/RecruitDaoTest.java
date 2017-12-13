package com.java.dao;

import com.java.BaseTest;
import com.xiaowo.Recruit;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/11/24
 */
public class RecruitDaoTest extends BaseTest {
    @Resource
    private RecruitDao recruitDao;

    @Test
    public void insertRecruit() throws Exception {
        Recruit recruit = new Recruit();
        recruit.setDepartmentId(1);
        recruit.setPositionId(2);
        recruit.setZoneId(1);
        recruit.setTitle("JAVA高级工程师");
        recruit.setDuty("1、负责公司现有系统的日常维护及新需求的开发；\n" +
                "2、根据项目计划高质量的完成功能开发；");
        recruit.setJobRequirements("1、精通Java、Javascript、xml、html等语言，精通J2EE等开发工具（Eclipse或JBuilder），熟悉Hibernate，Structs，WebWork开发框架；\n" +
                "2、熟悉J2EE技术体系（JSP、Servlet、EJB、JNDI、XML、SOAP、JMS等），熟悉Java的一些开源项目，熟悉主流的应用服务器和开发工具；");
        recruitDao.insertRecruit(recruit);
    }

    @Test
    public void findRecruit() throws Exception {
        List<Recruit> list = recruitDao.findRecruit();
        System.out.println(list);
    }

    @Test
    public void findRecruitById() throws Exception {
        Recruit recruit = recruitDao.findRecruitById(1);
        System.out.println(recruit);
    }

    @Test
    public void updateRecruit() throws Exception {
        Recruit recruit = recruitDao.findRecruitById(1);
        recruit.setTitle("java开发工程师");
        recruitDao.updateRecruit(recruit);
    }

    @Test
    public void publishRecruit() throws Exception {
    }

    @Test
    public void deleteRecruit() throws Exception {

    }

}