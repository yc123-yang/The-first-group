package com.sicnu.service.impl;

import com.sicnu.mapper.LevelMapper;
import com.sicnu.pojo.Level;
import com.sicnu.service.LevelService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LevelServiceImpl implements LevelService {
    private Result rs;
    @Resource
    LevelMapper levelMapper;
    @Override
    public Result addLevel(String level_name) {
        Level level =levelMapper.selectLevelByName(level_name);
        if (level ==null){
            levelMapper.addLevel(level_name);
            rs = new Result("0","添加等级成功",null);
        }else{
            rs = new Result("1","该等级已存在",null);

        }
        return rs;
    }

    @Override
    public Result delLevel(Integer level_id) {
        levelMapper.delLevel(level_id);
        rs = new Result("0","删除等级成功",null);
        return rs;
    }

    @Override
    public Result findAllLevel() {
        List<Level> levels =levelMapper.findAllLevel();
        rs = new Result("0",null,levels);
        return rs;
    }
}
