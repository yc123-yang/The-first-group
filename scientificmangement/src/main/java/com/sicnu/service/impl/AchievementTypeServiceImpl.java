package com.sicnu.service.impl;

import com.sicnu.mapper.AchievementTypeMapper;
import com.sicnu.pojo.AchievementType;
import com.sicnu.service.AchievementTypeService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *成果类型字典数据表
 */
@Service
public class AchievementTypeServiceImpl implements AchievementTypeService {
    @Resource
    AchievementTypeMapper achievementTypeMapper;

    private Result rs;

    /**
     * 添加成果类型数据
     * @param at_name
     * @return
     */
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

    /**
     * 根据类型id删除成果类型
     * @param at_id
     * @return
     */
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

    /**
     * 获取所有成果类型
     * @return
     */
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

    /**
     * 更新成果类型数据
     * @param achievementType
     * @return
     */
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
