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
    public Result addDepartmentType(String department_name) {

        try {
            Department department = departmentMapper.selectDepartmentTypeByName(department_name);
            if (department == null) {
                departmentMapper.addDepartmentType(department_name);
                rs = new Result("0", "部门添加成功", null);
            } else {
                rs = new Result("1", "该部门已存在", null);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delDepartmentType(Integer department_id) {
        try {
            departmentMapper.delDepartmentType(department_id);
            rs = new Result("0", "删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    @Override
    public Result findAllDepartmentType() {
        try {
            List<Department> departments = departmentMapper.findAllDepartmentType();
            rs = new Result("0", null, departments);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }
}
