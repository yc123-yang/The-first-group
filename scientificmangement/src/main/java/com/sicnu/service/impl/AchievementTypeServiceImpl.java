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

    private Result rs;

    @Override
    public Result addAchievementType(String at_name) {
        try {
            AchievementType achievementType = achievementTypeMapper.selectAchievementTypeByName(at_name);
            if (achievementType==null) {
                achievementTypeMapper.addAchievementType(at_name);
                rs = new Result("200", "插入成功", null);
            }else
            {
                rs = new Result("400", "字典数据已存在，切勿重复插入", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delAchievementType(Integer at_id) {

        try {
            achievementTypeMapper.delAchievementType(at_id);
            rs = new Result("200", "删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    @Override
    public Result findAllAchievementType() {
        try {
            List<AchievementType> achievementTypes = achievementTypeMapper.findAllAchievementType();
            rs = new Result("200", null, achievementTypes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result updateAchievementType(AchievementType achievementType) {
        try {
            achievementTypeMapper.updateAchievementType(achievementType);
            rs =  new Result("200","更改字典数据成功",null);
          } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
