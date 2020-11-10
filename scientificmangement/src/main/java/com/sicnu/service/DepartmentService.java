package com.sicnu.service;

import com.sicnu.pojo.Department;
import com.sicnu.util.Result;

import java.util.List;

public interface DepartmentService {
    Result addDepartmentType(String department_name);
    Result delDepartmentType(Integer department_id);
    Result findAllDepartmentType();
}
