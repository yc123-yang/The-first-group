package com.sicnu.service;

import com.sicnu.pojo.AchievementType;
import com.sicnu.util.Result;

import java.util.List;

public interface AchievementTypeService {
    Result addAchievementType(String at_name);
    Result delAchievementType(Integer at_id);
    Result findAllAchievementType();
}
