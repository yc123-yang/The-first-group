package com.sicnu.service;

import com.sicnu.pojo.Department;
import com.sicnu.util.Result;

import java.util.List;

public interface DepartmentService {
    Result addDepartment(String department_name);

    Result delDepartment(Integer department_id);

    Result findAllDepartment();
    Result updateDepartment(Department department);

}
