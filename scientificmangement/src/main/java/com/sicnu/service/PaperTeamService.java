package com.sicnu.service;

import com.sicnu.pojo.teamMap.PaperTeamMap;
import com.sicnu.util.Result;

import java.util.List;

public interface PaperTeamService {
    Result addPaperTeamUser(Integer paper_id,Integer user_id,String user_role,Double contribution,Integer department_id);
    Result delPaperTeamUser(Integer user_id);
    Result selectPaperTeam(Integer paper_id);
}
