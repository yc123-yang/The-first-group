package com.sicnu.service.impl;

import com.sicnu.mapper.AwardLevelMapper;
import com.sicnu.pojo.AwardLevel;
import com.sicnu.service.AwardLevelService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AwardLevelServiceImpl implements AwardLevelService {
    @Resource
    AwardLevelMapper awardLevelMapper;

    private Result rs;
    @Override
    public Result addAwardLevel(String al_name) {
        try {
            AwardLevel awardLevel = awardLevelMapper.selectAwardLevelByName(al_name);
            if (awardLevel.equals("")){
                awardLevelMapper.addAwardLevel(al_name);
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
            rs = new Result("200","删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findAllAwardLevel() {
        try {
            List<AwardLevel> awardLevels = awardLevelMapper.findAllAwardLevel();
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
                rs =new Result("200","更改成功",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
