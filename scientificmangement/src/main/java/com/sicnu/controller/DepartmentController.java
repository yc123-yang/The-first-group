package com.sicnu.controller;

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

    @PostMapping("/department/addDepartmentType")
    @RequiresPermissions("/data")
    public Result addDepartmentType(String department_name) {

        try {
            rs = departmentService.addDepartmentType(department_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/department/delDepartmentType")
    @RequiresPermissions("/data")
    public Result delDepartmentType(Integer department_id) {
        try {
            rs = departmentService.delDepartmentType(department_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/department/findAllDepartmentType")
    @RequiresPermissions("/data")
    public Result findAllDepartmentType() {
        try {
            rs = departmentService.findAllDepartmentType();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
