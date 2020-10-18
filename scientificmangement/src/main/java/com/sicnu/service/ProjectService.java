package com.sicnu.service;

import com.sicnu.util.Result;

import java.util.Date;

public interface ProjectService {
    Result selectProjectTeamByName(String project_name);
    Result checkProject(String project_stage, String project_type, Integer leader_id, String project_name, String project_abstract, String declaration, Date apply_time);
    Result findAllProject();
    Result updateProjectStage(String project_stage,Integer project_id,String project_name);
    Result updateProjectType(String project_type,Integer project_id,String project_name);
    Result delProject(Integer project_id);
    Result updateProject(Integer leader_id,String project_name,String project_stage,String project_type,Integer project_id);
    Result addProject(String project_stage, String project_type, Integer leader_id, String project_name, String project_abstract, String declaration, Date apply_time);

}
