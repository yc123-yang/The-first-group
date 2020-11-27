package com.sicnu.service;

import com.sicnu.pojo.DepartmentRank;
import com.sicnu.util.Result;

import java.util.List;

public interface DepartmentRankService {
    Result addDepartmentRank(String dr_name);
    Result delDepartmentRank(Integer dr_id);
    Result findAllDepartmentRank();
    Result updateDepartmentRank(DepartmentRank departmentRank);
}
