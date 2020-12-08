package com.sicnu.service;

import com.sicnu.pojo.PatentTeam;
import com.sicnu.util.Result;


public interface PatentTeamService {

    Result addPatentTeamUser(Integer patent_id,Integer user_id,  Integer contribution);
    Result delPatentTeamUser(Integer patent_id,Integer user_id);
    Result selectPatentTeam(Integer patent_id);
    Result updatePatentTeamUser(PatentTeam patentTeam);

}
