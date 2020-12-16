package com.sicnu.service.impl;

import com.sicnu.component.RedisUtil;
import com.sicnu.mapper.PressLevelMapper;
import com.sicnu.pojo.PressLevel;
import com.sicnu.service.PressLevelService;
import com.sicnu.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class PressLevelServiceImpl implements PressLevelService {

    @Resource
    PressLevelMapper pressLevelMapper;
    @Resource
    private RedisUtil.redisList redisList;

    @Autowired
    private RedisUtil redisUtil;
    private Result rs;
    @Override
    public Result addPressLevel(String pl_name,Integer bpl_score) {
        try {
            PressLevel pressLevel = pressLevelMapper.selectPressLevelByName(pl_name);
            if (pressLevel!=null){
                rs = new Result("400","该字典已经存在",null);
            }else {
                pressLevelMapper.addPressLevel(pl_name,bpl_score);
                redisUtil.del("pressLevels");
                rs = new Result("200","添加成功",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delPressLevel(Integer pl_id) {
        try {
            pressLevelMapper.delPressLevel(pl_id);
            redisUtil.del("pressLevels");
            rs = new Result("200","删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findAllPressLevel() {
        try {
            List pressLevels = pressLevelMapper.findAllPressLevel();
            if (redisUtil.hasKey("pressLevels")) {
                log.warn("从redis中获取数据.");
                pressLevels = redisList.get("pressLevels", 0, -1);
            } else {
                pressLevels = pressLevelMapper.findAllPressLevel();
                log.warn("从数据库中获取数据.");
                log.warn("将数据存入redis...");
                redisList.set("pressLevels", pressLevels);
                log.info("成功存入redis.");
            }
            rs = new Result("200",null,pressLevels);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result updatePressLevel(PressLevel pressLevel) {
        try {
            pressLevelMapper.updatePressLevel(pressLevel);
            redisUtil.del("pressLevels");
            rs = new Result("200","更改成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }


}
