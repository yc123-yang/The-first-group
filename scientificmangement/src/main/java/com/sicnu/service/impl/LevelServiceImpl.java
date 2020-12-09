package com.sicnu.service.impl;

import com.sicnu.mapper.LevelMapper;
import com.sicnu.pojo.Level;
import com.sicnu.service.LevelService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 项目等级
 */
@Service
public class LevelServiceImpl implements LevelService {
    private Result rs;
    @Resource
    LevelMapper levelMapper;

    @Override
    public Result addLevel(String level_name,Integer pl_score) {
        try {
            Level level = levelMapper.selectLevelByName(level_name);
            if (level == null) {
                levelMapper.addLevel(level_name,pl_score);
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
            rs = new Result("200", "删除等级成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findAllLevel() {
        try {
            List<Level> levels = levelMapper.findAllLevel();
            rs = new Result("200", null, levels);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result updateLevel(Level level) {
        try {
           levelMapper.updateLevel(level);
            rs = new Result("200", "更改成功",null );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
