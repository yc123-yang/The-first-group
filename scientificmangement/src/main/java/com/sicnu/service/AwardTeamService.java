package com.sicnu.service;

import com.sicnu.pojo.Award;
import com.sicnu.pojo.AwardTeam;
import com.sicnu.util.Result;

public interface AwardTeamService {
    Result addAwardTeamUser(Integer award_id, Integer user_id, Integer contribution);
    Result delAwardTeamUser(Integer award_id,Integer user_id);
    Result selectAwardTeam(Integer award_id);
    Result updateAwardTeamUser(AwardTeam awardTeam);

}
