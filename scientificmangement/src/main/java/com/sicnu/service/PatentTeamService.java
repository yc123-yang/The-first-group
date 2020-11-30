package com.sicnu.service;

import com.sicnu.pojo.teamMap.BookTeamMap;
import com.sicnu.util.Result;

import java.util.List;

public interface PatentTeamService {

    Result addPatentTeamUser(Integer patent_id,String user_name, String user_role, Double contribution,Integer department_id);
    Result delPatentTeamUser(Integer user_id);
    Result selectPatentTeam(Integer patent_id);
}
