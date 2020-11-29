package com.sicnu.controller;

import com.sicnu.pojo.Department;
import com.sicnu.service.impl.DepartmentServiceImpl;
import com.sicnu.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@CrossOrigin
@ResponseBody
public class DepartmentController {

    @Resource
    DepartmentServiceImpl departmentService;
    private Result rs;

    @PostMapping("/department/addDepartment")
    @RequiresPermissions("/data")
    public Result addDepartmentType(String department_name) {

        try {
            rs = departmentService.addDepartment(department_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/department/delDepartment")
    @RequiresPermissions("/data")
    public Result delDepartmentType(Integer department_id) {
        try {
            rs = departmentService.delDepartment(department_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/department/findAllDepartment")
    public Result findAllDepartment() {
        try {
            rs = departmentService.findAllDepartment();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/department/updateDepartment")
    @RequiresPermissions("/data")
    public Result updateDepartment(Department department) {
        try {
           rs= departmentService.updateDepartment(department);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }
}
