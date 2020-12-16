package com.sicnu.service.impl;

import com.sicnu.component.RedisUtil;
import com.sicnu.mapper.PatentRangeMapper;
import com.sicnu.pojo.PatentRange;
import com.sicnu.pojo.ResearchType;
import com.sicnu.service.PatentRangeService;
import com.sicnu.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PatentRangeServiceImpl implements PatentRangeService {

    @Resource
    PatentRangeMapper patentRangeMapper;
    @Resource
    private RedisUtil.redisList redisList;

    @Autowired
    private RedisUtil redisUtil;
    private Result rs;
    @Override
    public Result addPatentRange(String pr_name) {
        try {
            PatentRange patentRange = patentRangeMapper.selectPatentRangeByName(pr_name);
            if (patentRange==null){
                patentRangeMapper.addPatentRange(pr_name);
                redisUtil.del("patentRanges");
                rs= new Result("200","插入字典数据成功",null);
            }else{
                rs= new Result("200","字典数据已经存在，切勿重复插入",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delPatentRange(Integer pr_id) {
        try {
            patentRangeMapper.delPatentRange(pr_id);
            redisUtil.del("patentRanges");
            rs= new Result("200","删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findAllPatentRange() {
        try {
            List patentRanges = new ArrayList();
            if (redisUtil.hasKey("patentRanges")) {
                log.warn("从redis中获取数据.");
                patentRanges = redisList.get("patentRanges", 0, -1);
            } else {
                patentRanges = patentRangeMapper.findAllPatentRange();
                log.warn("从数据库中获取数据.");
                log.warn("将数据存入redis...");
                redisList.set("patentRanges", patentRanges);
                log.info("成功存入redis.");
            }
            rs= new Result("200",null,patentRanges);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result updatePatentRange(PatentRange patentRange) {
        if (patentRange.getPr_name().equals("")){
            rs= new Result("400","更改数据不能为空",null);
        }else {
            patentRangeMapper.updatePatentRange(patentRange);
            redisUtil.del("patentRanges");
            rs= new Result("200","更改成功",null);
        }
        return rs;
    }
}
