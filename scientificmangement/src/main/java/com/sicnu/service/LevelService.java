package com.sicnu.service;

import com.sicnu.pojo.Level;
import com.sicnu.util.Result;

import java.util.List;

public interface LevelService {
    Result addLevel(String level_name);
    Result delLevel(Integer level_id);
    Result findAllLevel();
}
