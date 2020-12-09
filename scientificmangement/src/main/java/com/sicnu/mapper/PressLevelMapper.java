package com.sicnu.mapper;

import com.sicnu.pojo.PressLevel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PressLevelMapper {

    void addPressLevel(String pl_name,Integer bpl_score );
    void delPressLevel(Integer pl_id);
    List<PressLevel> findAllPressLevel();
    PressLevel selectPressLevelByName(String pl_name);
    void updatePressLevel(PressLevel pressLevel);
    Integer selectPressLevelScoreById(Integer pl_id);
}
