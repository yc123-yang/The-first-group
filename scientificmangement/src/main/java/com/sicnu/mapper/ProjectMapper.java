package com.sicnu.mapper;

import com.sicnu.pojo.Project;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ProjectMapper {
    void addProject(Project project);

    List<Project> selectProjectByCondition(Map<String, Object> map);

    void delProject(Integer project_id);

    void updateProject(Project project);

    Integer selectProjectId(Integer leader_id, String project_name);

    List<Project> selectTeamByPid(Integer project_Id);

    Integer selectTotalProject(Map<String, Object> map);

    Project findProjectById(Integer project_id);
    Integer selectCountProject(Map<String, Object> map);
    List<Project> findProjectByLeaderId(Integer leader_id);
}
