package com.sicnu.mapper;

import com.sicnu.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DepartmentMapper {
    void addDepartmentType(String department_name);
    void delDepartmentType(Integer department_id);
    List<Department> findAllDepartmentType();
    Department selectDepartmentTypeByName(String department_name);
}
