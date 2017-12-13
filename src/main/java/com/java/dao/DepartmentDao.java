package com.java.dao;

import com.xiaowo.Department;

import java.util.List;

/**
 * @author answer
 *         2017/11/24
 */
public interface DepartmentDao {
    /**
     * 新增部门
     *
     * @param department
     * @return
     */
    int insertDepartment(Department department);

    /**
     * 查询所有的部门
     *
     * @return
     */
    List<Department> findDepartments();

    /**
     * 查询跟当前部门不同名字的部门名称
     *
     * @param id
     * @return
     */
    List<String> findNoSameDepartment(Integer id);

    /**
     * 根据部门查询
     *
     * @param department
     * @return
     */
    Department findDepartmentByDepartment(String department);

    /**
     * 根据id查询部门
     *
     * @param id
     * @return
     */
    Department findDepartmentById(Integer id);

    /**
     * 更新部门信息
     *
     * @param department
     * @return
     */
    int updateDepartment(Department department);

    /**
     * 删除部门
     *
     * @param id
     * @return
     */
    int deleteDepartment(Integer id);
}
