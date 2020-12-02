package com.sicnu.service;

import com.sicnu.util.Result;


public interface PatentTeamService {

    Result addPatentTeamUser(Integer patent_id,Integer user_id, String user_role, Double contribution,Integer department_id);
    Result delPatentTeamUser(Integer user_id);
    Result selectPatentTeam(Integer patent_id);
}
