package com.sicnu.service.impl;

import com.sicnu.component.RedisUtil;
import com.sicnu.mapper.AwardLevelMapper;
import com.sicnu.pojo.AwardLevel;
import com.sicnu.service.AwardLevelService;
import com.sicnu.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AwardLevelServiceImpl implements AwardLevelService {
    @Resource
    AwardLevelMapper awardLevelMapper;

    @Resource
    private RedisUtil.redisList redisList;

    @Autowired
    private RedisUtil redisUtil;

    private Result rs;
    @Override
    public Result addAwardLevel(String al_name,Integer al_score) {
        try {
            AwardLevel awardLevel = awardLevelMapper.selectAwardLevelByName(al_name);
            if (awardLevel==null){
                awardLevelMapper.addAwardLevel(al_name,al_score);
                redisUtil.del("awardLevels");
                rs = new Result("200","插入成功",null);
            }else{
                rs = new Result("400","该字典数据已经存在，请勿重复插入",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delAwardLevel(Integer al_id) {
        try {
            awardLevelMapper.delAwardLevel(al_id);
            redisUtil.del("awardLevels");
            rs = new Result("200","删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findAllAwardLevel() {
        try {
            List awardLevels = new ArrayList<>();
            if (redisUtil.hasKey("awardLevels")) {
                log.warn("从redis中获取数据.");
                awardLevels = redisList.get("awardLevels", 0, -1);
            } else {
                awardLevels = awardLevelMapper.findAllAwardLevel();
                log.warn("从数据库中获取数据.");
                log.warn("将数据存入redis...");
                redisList.set("awardLevels", awardLevels);
                log.info("成功存入redis.");
            }

            rs = new Result("200",null,awardLevels);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result updateAwardLevel(AwardLevel awardLevel) {
        try {
            if (awardLevel.getAl_name().equals("")){
                rs = new Result("400","更改数据不能为空",null);
            }else{
                awardLevelMapper.updateAwardLevel(awardLevel);
                redisUtil.del("awardLevels");
                rs =new Result("200","更改成功",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
