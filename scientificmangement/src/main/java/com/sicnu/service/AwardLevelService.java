package com.sicnu.service;

import com.sicnu.pojo.AwardLevel;
import com.sicnu.util.Result;

import java.util.List;

public interface AwardLevelService {
    Result addAwardLevel(String al_name,Integer al_score);
    Result delAwardLevel(Integer al_id);
    Result findAllAwardLevel();
    Result updateAwardLevel(AwardLevel awardLevel);
}
