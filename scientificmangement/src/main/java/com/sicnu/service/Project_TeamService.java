package com.sicnu.service;

import com.sicnu.util.Result;

import java.util.Date;

public interface Project_TeamService {
    Result addProjectTeam(Integer project_id, Integer user_id, Date join_time);
    Result delProjectTeam(Integer project_id);
    Result delTeamUser(Integer user_id);
}
