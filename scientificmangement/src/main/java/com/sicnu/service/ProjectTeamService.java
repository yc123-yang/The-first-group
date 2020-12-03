package com.sicnu.service;


import com.sicnu.util.Result;



public interface ProjectTeamService {
    Result addProjectTeamUser(Integer project_id, Integer user_id,String team_role);

    Result delProjectTeamUser(Integer user_id);

    Result selectProjectTeam(Integer project_id);

}
