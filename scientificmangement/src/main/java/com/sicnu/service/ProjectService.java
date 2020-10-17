package com.sicnu.service;

import com.sicnu.pojo.Project;
import com.sicnu.util.Result;

import java.util.Date;
import java.util.List;

public interface ProjectService {
    Result selectProjectByName(String project_name);
    Result checkProject(String project_stage, String project_type, Integer leader_id, String project_name, String project_abstract, String declaration, Date apply_time);
    Result findAllProject();
    Result updateProjectStage(String project_stage);
    Result updateProjectType(String project_type);
    Result delProject(Integer project_id);
    Result updateProject(Integer leader_id,String project_name,String project_stage,String project_type,Integer project_id);
    Result addProject(String project_stage, String project_type, Integer leader_id, String project_name, String project_abstract, String declaration, Date apply_time);

}
