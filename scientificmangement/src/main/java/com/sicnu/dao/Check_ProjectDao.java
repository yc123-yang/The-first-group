package com.sicnu.dao;

import com.sicnu.pojo.Check_Project;
import com.sicnu.pojo.Project;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface Check_ProjectDao {
    void addProject(String project_stage, String project_type, Integer leader_id, String project_name, String project_abstract, String declaration, Date apply_time);

    List<Check_Project> findAllProject();
    void delCheckProjectById(Integer project_id);
}
