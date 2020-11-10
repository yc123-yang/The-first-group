package com.sicnu.service.impl;

import com.sicnu.mapper.DepartmentMapper;
import com.sicnu.pojo.Department;
import com.sicnu.service.DepartmentService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    DepartmentMapper departmentMapper;

    private Result rs;
    @Override
    public Result addDepartmentType(String department_name) {

        Department department =departmentMapper.selectDepartmentTypeByName(department_name);
        if (department==null){
            departmentMapper.addDepartmentType(department_name);
            rs = new Result("0","部门添加成功",null);
        }else{
            rs = new Result("1","该部门已存在",null);

        }
        return rs;
    }

    @Override
    public Result delDepartmentType(Integer department_id) {
        departmentMapper.delDepartmentType(department_id);
        rs = new Result("0","删除成功",null);

        return rs;
    }

    @Override
    public Result findAllDepartmentType() {
        List<Department> departments = departmentMapper.findAllDepartmentType();
        rs = new Result("0",null,departments);

        return rs;
    }
}
