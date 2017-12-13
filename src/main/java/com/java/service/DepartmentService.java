package com.java.service;

import com.xiaowo.Department;

import java.util.List;

/**
 * @author answer
 *         2017/11/24
 */
public interface DepartmentService {
    /**
     * 查询所有的部门
     *
     * @return
     */
    List<Department> findDepartments();

    /**
     * 删除部门
     *
     * @param id
     */
    void deleteDepartment(Integer id);

    /**
     * 根据id查询部门信息
     *
     * @param id
     * @return
     */
    Department findDepartmentById(Integer id);

    /**
     * 更新部门
     *
     * @param id
     * @param department
     * @param remark
     * @return
     */
    int updateDepartment(Integer id, String department, String remark);

    /**
     * 新增部门
     *
     * @param department
     * @param remark
     * @return
     */
    int saveDepartment(String department, String remark);
}
