package com.sicnu.mapper;

import com.sicnu.pojo.DepartmentRank;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DepartmentRankMapper {
    void addDepartmentRank(String dr_name);
    void delDepartmentRank(Integer dr_id);
    List<DepartmentRank> findAllDepartmentRank();
    DepartmentRank selectDepartmentRankByName(String dr_name);
    void updateDepartmentRank(DepartmentRank departmentRank);
}
