package com.sicnu.service.impl;

import com.sicnu.mapper.DepartmentMapper;
import com.sicnu.pojo.Department;
import com.sicnu.service.DepartmentService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * l用户来源部门
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    DepartmentMapper departmentMapper;

    private Result rs;

    @Override
    public Result addDepartment(String department_name) {

        try {
            Department department = departmentMapper.selectDepartmentByName(department_name);
            if (department == null) {
                departmentMapper.addDepartment(department_name);
                rs = new Result("200", "部门添加成功", null);
            } else {
                rs = new Result("400", "该部门已存在", null);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delDepartment(Integer department_id) {
        try {
            departmentMapper.delDepartment(department_id);
            rs = new Result("200", "删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    @Override
    public Result findAllDepartment() {
        try {
            List<Department> departments = departmentMapper.findAllDepartment();
            rs = new Result("200", null, departments);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    @Override
    public Result updateDepartment(Department department) {
        try {
             departmentMapper.updateDepartment(department);
            rs = new Result("200", "更改成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }
}
