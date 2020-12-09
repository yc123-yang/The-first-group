package com.sicnu.service.impl;

import com.sicnu.mapper.PressLevelMapper;
import com.sicnu.pojo.PressLevel;
import com.sicnu.service.PressLevelService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PressLevelServiceImpl implements PressLevelService {

    @Resource
    PressLevelMapper pressLevelMapper;

    private Result rs;
    @Override
    public Result addPressLevel(String pl_name,Integer bpl_score) {
        try {
            PressLevel pressLevel = pressLevelMapper.selectPressLevelByName(pl_name);
            if (pressLevel!=null){
                rs = new Result("400","该字典已经存在",null);
            }else {
                pressLevelMapper.addPressLevel(pl_name,bpl_score);
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
            rs = new Result("200","删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findAllPressLevel() {
        try {
            List<PressLevel> pressLevels = pressLevelMapper.findAllPressLevel();
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
            rs = new Result("200","更改成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }


}
