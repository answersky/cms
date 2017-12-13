package com.java.dao;

import com.java.BaseTest;
import com.xiaowo.Department;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/11/24
 */
public class DepartmentDaoTest extends BaseTest {
    @Resource
    private DepartmentDao departmentDao;

    @Test
    public void insertDepartment() throws Exception {
        Department department = new Department();
        department.setDepartment("风控部");
        department.setRemark("技术中心");
        departmentDao.insertDepartment(department);
    }

    @Test
    public void findDepartments() throws Exception {
        List<Department> departments = departmentDao.findDepartments();
        System.out.println(departments);

    }

    @Test
    public void findDepartmentById() throws Exception {
        Department department = departmentDao.findDepartmentById(2);
        System.out.println(department);
    }

    @Test
    public void updateDepartment() throws Exception {
        Department department = departmentDao.findDepartmentById(2);
        department.setRemark("风控中心");
        departmentDao.updateDepartment(department);
    }

    @Test
    public void deleteDepartment() throws Exception {
        departmentDao.deleteDepartment(2);
    }

    @Test
    public void findNoSameDepartment() throws Exception {
        List<String> names = departmentDao.findNoSameDepartment(1);
        System.out.println(names);
    }

}