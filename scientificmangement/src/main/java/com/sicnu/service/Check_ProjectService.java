package com.sicnu.service;

import com.sicnu.util.Result;

import java.util.Date;

public interface Check_ProjectService {

    Result findAllProject();
    Result checkProject(String project_stage, String project_type, Integer leader_id, String project_name, String project_abstract, String declaration, Date apply_time);

}
