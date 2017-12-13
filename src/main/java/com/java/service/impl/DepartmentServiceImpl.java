package com.java.service.impl;

import com.java.dao.DepartmentDao;
import com.java.service.DepartmentService;
import com.xiaowo.Department;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/11/24
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private DepartmentDao departmentDao;

    @Override
    public List<Department> findDepartments() {
        return departmentDao.findDepartments();
    }

    @Override
    public void deleteDepartment(Integer id) {
        departmentDao.deleteDepartment(id);
    }

    @Override
    public Department findDepartmentById(Integer id) {
        return departmentDao.findDepartmentById(id);
    }

    @Override
    public int updateDepartment(Integer id, String department, String remark) {
        Department dep = departmentDao.findDepartmentById(id);
        List<String> departments = departmentDao.findNoSameDepartment(id);
        if (departments != null && departments.size() > 0) {
            if (departments.indexOf(department) >= 0) {
                //名字重复，不符合要求
                return 0;
            }
        }
        dep.setDepartment(department);
        dep.setRemark(remark);
        return departmentDao.updateDepartment(dep);
    }

    @Override
    public int saveDepartment(String department, String remark) {
        Department dep = departmentDao.findDepartmentByDepartment(department);
        if (dep != null) {
            return 0;
        }
        Department depart = new Department();
        depart.setDepartment(department);
        depart.setRemark(remark);
        return departmentDao.insertDepartment(depart);
    }
}
