package com.sicnu.service.impl;

import com.sicnu.component.RedisUtil;
import com.sicnu.mapper.ConclusionTypeMapper;
import com.sicnu.pojo.ConclusionType;
import com.sicnu.service.ConclusionTypeService;
import com.sicnu.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/*
结题
 */
@Service
@Slf4j
public class ConclusionTypeServiceImpl implements ConclusionTypeService {
    @Resource
    ConclusionTypeMapper conclusionTypeMapper;

    @Resource
    private RedisUtil.redisList redisList;

    @Autowired
    private RedisUtil redisUtil;

    private Result rs;

    @Override
    public Result addConclusionType(String ct_name) {
        try {
            ConclusionType conclusionType = conclusionTypeMapper.selectConclusionTypeByName(ct_name);
            if (conclusionType == null) {
                conclusionTypeMapper.addConclusionType(ct_name);
                redisUtil.del("conclusionTypes");
                rs = new Result("200", "添加成功", null);
            } else {
                rs = new Result("400", "该字典已存在", null);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delConclusionType(Integer ct_id) {
        try {
            conclusionTypeMapper.delConclusionType(ct_id);
            redisUtil.del("conclusionTypes");
            rs = new Result("200", "删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findAllConclusionType() {
        try {
            List conclusionTypes = new ArrayList();

            if (redisUtil.hasKey("conclusionTypes")) {
                log.warn("从redis中获取数据.");
                conclusionTypes = redisList.get("conclusionTypes", 0, -1);
            } else {
                conclusionTypes = conclusionTypeMapper.findAllConclusionType();
                log.warn("从数据库中获取数据.");
                log.warn("将数据存入redis...");
                redisList.set("conclusionTypes", conclusionTypes);
                log.info("成功存入redis.");
            }
            rs = new Result("200", null, conclusionTypes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result updateConclusionType(ConclusionType conclusionType) {
        try {
            conclusionTypeMapper.updateConclusionType(conclusionType);
            redisUtil.del("conclusionTypes");
            rs = new Result("20","更改成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
