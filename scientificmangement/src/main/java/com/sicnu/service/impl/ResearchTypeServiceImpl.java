package com.sicnu.service.impl;

import com.sicnu.component.RedisUtil;
import com.sicnu.mapper.PublicationPlaceMapper;
import com.sicnu.mapper.ResearchTypeMapper;
import com.sicnu.pojo.ResearchType;
import com.sicnu.service.ResearchTypeService;
import com.sicnu.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ResearchTypeServiceImpl implements ResearchTypeService {
    @Resource
    private RedisUtil.redisList redisList;

    @Autowired
    private RedisUtil redisUtil;
    @Resource
    ResearchTypeMapper researchTypeMapper;

    private Result rs;


    @Override
    public Result addResearchType(String rt_name) {
        try {
            ResearchType researchType = researchTypeMapper.selectResearchTypeByName(rt_name);
            if (researchType==null){
                researchTypeMapper.addResearchType(rt_name);
                redisUtil.del("researchTypes");
                rs = new Result("200","字典数据插入成功",null);
            }else{
                rs = new Result("400","字典数据已存在，请勿重复插入",null);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delResearchType(Integer rt_id) {
        try {
            researchTypeMapper.delResearchType(rt_id);
            redisUtil.del("researchTypes");
            rs = new Result("200","删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findAllResearchType() {
        try {
            List researchTypes = new ArrayList();
            if (redisUtil.hasKey("researchTypes")) {
                log.warn("从redis中获取数据.");
                researchTypes = redisList.get("researchTypes", 0, -1);
            } else {
                researchTypes = researchTypeMapper.findAllResearchType();
                log.warn("从数据库中获取数据.");
                log.warn("将数据存入redis...");
                redisList.set("researchTypes", researchTypes);
                log.info("成功存入redis.");
            }
            rs = new Result("200",null,researchTypes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result updateResearchType(ResearchType researchType) {
        if (researchType.getRt_name().equals("")){
            rs = new Result("400","字典数据不能为空",null);
        }else{
            researchTypeMapper.updateResearchType(researchType);
            redisUtil.del("researchTypes");
            rs = new Result("200","更改字典数据成功",null);
        }
        return rs;
    }
}
