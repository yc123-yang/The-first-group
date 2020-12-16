package com.sicnu.service.impl;

import com.sicnu.component.RedisUtil;
import com.sicnu.mapper.PeriodicalMapper;
import com.sicnu.pojo.Periodical;
import com.sicnu.service.PeriodicalService;
import com.sicnu.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PeriodicalServiceImpl implements PeriodicalService {
    @Resource
    PeriodicalMapper periodicalMapper;
    @Resource
    private RedisUtil.redisList redisList;

    @Autowired
    private RedisUtil redisUtil;
    private Result rs;
    @Override
    public Result addPeriodical(String periodical_name,Integer pp_score) {
        try {
            Periodical periodical = periodicalMapper.selectPeriodicalByName(periodical_name);
            if (periodical!=null){
                rs = new Result("401","该字典已经存在",null);
            }else {
                periodicalMapper.addPeriodical(periodical_name,pp_score);
                redisUtil.del("periodicals");
                rs = new Result("200","期刊字典插入成功",null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delPeriodical(Integer pt_id) {

        try {
            periodicalMapper.delPeriodical(pt_id);
            redisUtil.del("periodicals");
            rs = new Result("200","删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findAllPeriodical() {
        try {
            List periodicals = new ArrayList<>();
            if (redisUtil.hasKey("periodicals")) {
                log.warn("从redis中获取数据.");
                periodicals = redisList.get("periodicals", 0, -1);
            } else {
                periodicals = periodicalMapper.findAllPeriodical();
                log.warn("从数据库中获取数据.");
                log.warn("将数据存入redis...");
                redisList.set("periodicals", periodicals);
                log.info("成功存入redis.");
            }
            rs = new Result("200",null,periodicals);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result updatePeriodical(Periodical periodical) {
        try {
            periodicalMapper.updatePeriodical(periodical);
            redisUtil.del("periodicals");
            rs = new Result("200","更改成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
