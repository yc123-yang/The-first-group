package com.sicnu.service;

import com.sicnu.util.Result;

public interface AwardTeamService {
    Result addAwardTeamUser(Integer award_id, String user_name, String user_role, Double contribution,Integer department_id);
    Result delAwardTeamUser(Integer user_id);
    Result selectAwardTeam(Integer award_id);
}
