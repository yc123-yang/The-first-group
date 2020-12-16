package com.sicnu.service.impl;

import com.sicnu.component.RedisUtil;
import com.sicnu.mapper.NatureMapper;
import com.sicnu.pojo.Nature;
import com.sicnu.service.NatureService;
import com.sicnu.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 一项目性质
 */
@Service
@Slf4j
public class NatureServiceImpl implements NatureService {
    @Resource
    NatureMapper natureMapper;

    private Result rs;

    @Resource
    private RedisUtil.redisList redisList;

    @Autowired
    private RedisUtil redisUtil;
    @Override
    public Result addNature(String nature_name) {
        try {
            Nature nature = natureMapper.selectNatureByName(nature_name);
            if (nature == null) {
                natureMapper.addNature(nature_name);
                redisUtil.del("natures");
                rs = new Result("200", "添加项目性质成功", null);
            } else {
                rs = new Result("400", "项目性质已经存在", null);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delNature(Integer nature_id) {
        try {
            natureMapper.delNature(nature_id);
            redisUtil.del("natures");
            rs = new Result("200", "删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findAllNature() {
        try {
            List natures = new ArrayList();

            if (redisUtil.hasKey("natures")) {
                log.warn("从redis中获取数据.");
                natures = redisList.get("natures", 0, -1);
            } else {
                natures = natureMapper.findAllNature();
                log.warn("从数据库中获取数据.");
                log.warn("将数据存入redis...");
                redisList.set("natures", natures);
                log.info("成功存入redis.");
            }
            rs = new Result("200", null, natures);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result updateNature(Nature nature) {
        try {
            if (nature!=null){
                natureMapper.updateNature(nature);
                redisUtil.del("natures");
                rs = new Result("200", "更改成功", null);
            }else{
                rs = new Result("400","更改数据不能为空",null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
