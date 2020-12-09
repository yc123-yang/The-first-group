package com.sicnu.service;

import com.sicnu.pojo.PressLevel;
import com.sicnu.util.Result;

import java.util.List;

public interface PressLevelService {
    Result addPressLevel(String pl_name,Integer bpl_score);
    Result delPressLevel(Integer pl_id);
    Result findAllPressLevel();
    Result updatePressLevel(PressLevel pressLevel);

}
