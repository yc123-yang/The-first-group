package com.sicnu.service;

import com.sicnu.util.Result;


public interface PatentTeamService {

    Result addPatentTeamUser(Integer patent_id,Integer user_id,  Double contribution);
    Result delPatentTeamUser(Integer user_id);
    Result selectPatentTeam(Integer patent_id);
}
