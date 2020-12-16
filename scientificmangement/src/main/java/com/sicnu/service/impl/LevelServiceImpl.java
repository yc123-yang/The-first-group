package com.sicnu.service.impl;

import com.sicnu.component.RedisUtil;
import com.sicnu.mapper.LevelMapper;
import com.sicnu.pojo.Level;
import com.sicnu.service.LevelService;
import com.sicnu.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目等级
 */
@Service
@Slf4j
public class LevelServiceImpl implements LevelService {

    private Result rs;

    @Resource
    LevelMapper levelMapper;

    @Resource
    private RedisUtil.redisList redisList;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Result addLevel(String level_name,Integer pl_score) {
        try {
            Level level = levelMapper.selectLevelByName(level_name);
            if (level == null) {
                levelMapper.addLevel(level_name,pl_score);
                redisUtil.del("levels");
                rs = new Result("200", "添加等级成功", null);
            } else {
                rs = new Result("400", "该等级已存在", null);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delLevel(Integer level_id) {
        try {
            levelMapper.delLevel(level_id);
            redisUtil.del("levels");
            rs = new Result("200", "删除等级成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findAllLevel() {
        try {
            List levels = new ArrayList();
            if (redisUtil.hasKey("levels")) {
                log.warn("从redis中获取数据.");
                levels = redisList.get("levels", 0, -1);
            } else {
                levels = levelMapper.findAllLevel();
                log.warn("从数据库中获取数据.");
                log.warn("将数据存入redis...");
                redisList.set("levels", levels);
                log.info("成功存入redis.");
            }
            rs = new Result("200", null, levels);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result updateLevel(Level level) {
        try {
            if(level!=null){
                levelMapper.updateLevel(level);
                redisUtil.del("levels");
                rs = new Result("200", "更改成功",null );
            }else{
                rs = new Result("400","更改数据不能为空",null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
