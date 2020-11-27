package com.sicnu.service;

import com.sicnu.pojo.PressLevel;
import com.sicnu.util.Result;

import java.util.List;

public interface PressLevelService {
    Result addPressLevel(String pl_name);
    Result delPressLevel(Integer pl_id);
    Result findAllPressLevel();
}
