package com.sicnu.mapper;

import com.sicnu.pojo.Project;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProjectMapper {
    void addProject(Project project);
    List<Project> selectProject(Project project);
    void delProject(Integer project_id);
    void updateProject(Project project);
    Integer selectProjectId(Integer leader_id,String project_name);
    List<Project> selectTeamByPid(Integer project_Id);
    List<Project> findAllProject();
}
