package com.sicnu.service.impl;

import com.sicnu.mapper.AchievementTypeMapper;
import com.sicnu.pojo.AchievementType;
import com.sicnu.service.AchievementTypeService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 
 */
@Service
public class AchievementTypeServiceImpl implements AchievementTypeService {
    @Resource
    AchievementTypeMapper achievementTypeMapper;

    private Result rs ;
    @Override
    public Result addAchievementType(String at_name) {
        AchievementType achievementType =achievementTypeMapper.selectAchievementTypeByName(at_name);
        if (achievementType==null){
            achievementTypeMapper.addAchievementType(at_name);
            rs =new Result("0","插入成功",null);
        }{
            rs =new Result("1","已经存在",null);

        }
        return rs;
    }

    @Override
    public Result delAchievementType(Integer at_id) {

        achievementTypeMapper.delAchievementType(at_id);
        rs =new Result("0","删除成功",null);

        return rs;
    }

    @Override
    public Result findAllAchievementType() {
        List<AchievementType> achievementTypes = achievementTypeMapper.findAllAchievementType();
        rs =new Result("0",null,achievementTypes);
        return rs;
    }
}
