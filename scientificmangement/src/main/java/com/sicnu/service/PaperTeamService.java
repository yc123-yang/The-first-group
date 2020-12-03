package com.sicnu.service;

import com.sicnu.pojo.teamMap.PaperTeamMap;
import com.sicnu.util.Result;

import java.util.List;

public interface PaperTeamService {
    Result addPaperTeamUser(Integer paper_id,Integer user_id,Double contribution);
    Result delPaperTeamUser(Integer user_id);
    Result selectPaperTeam(Integer paper_id);
}
