package com.sicnu.dao;

import com.sicnu.pojo.Project;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface ProjectDao {
    List<Project> selectProjectById(String project_name);
    void addProject(String project_stage, String project_type, Integer leader_id, String project_name, String project_abstract, String declaration, Date apply_time);
    List<Project> findAllProject();
    void updateProjectStage(String project_stage);
    void updateProjectType(String project_type);
    void delProject(Integer project_id);
    void updateProject(Integer leader_id,String project_name,String project_stage,String project_type,Integer project_id);
}
