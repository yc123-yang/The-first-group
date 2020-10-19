package com.sicnu.service;

import com.sicnu.pojo.Project;
import com.sicnu.util.Result;

import java.util.Date;
import java.util.List;

public interface ProjectService {
    Result selectProjectTeamByName(String project_name);
    Result findAllProject();
    Result updateProjectStage(String project_stage,Integer project_id,String project_name);
    Result updateProjectType(String project_type,Integer project_id,String project_name);
    Result delProject(Integer project_id,Integer leader_id,String project_name);
    Result updateProject(Integer leader_id,String project_name,String project_stage,String project_type,Integer project_id);
    Result addProject(String project_stage, String project_type, Integer leader_id, String project_name, String project_abstract, String declaration, Date apply_time);

}
