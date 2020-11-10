package com.sicnu.controller;

import com.sicnu.pojo.Department;
import com.sicnu.service.DepartmentService;
import com.sicnu.service.impl.DepartmentServiceImpl;
import com.sicnu.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@CrossOrigin
@ResponseBody
public class DepartmentController {

    @Resource
    DepartmentServiceImpl departmentService;
    private Result rs;
    @PostMapping("/department/addDepartmentType")
    public Result addDepartmentType(String department_name) {

        rs =departmentService.addDepartmentType(department_name);
        return rs;
    }

    @PostMapping("/department/delDepartmentType")
    public Result delDepartmentType(Integer department_id) {
        rs = departmentService.delDepartmentType(department_id);
        return rs;
    }

    @PostMapping("/department/findAllDepartmentType")
    public Result findAllDepartmentType() {
        rs  = departmentService.findAllDepartmentType();
        return rs;
    }
}
