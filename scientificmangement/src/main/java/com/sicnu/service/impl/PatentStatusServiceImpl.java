package com.sicnu.service.impl;

import com.sicnu.component.RedisUtil;
import com.sicnu.mapper.PatentStatusMapper;
import com.sicnu.pojo.PatentStatus;
import com.sicnu.service.PatentStatusService;
import com.sicnu.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
public class PatentStatusServiceImpl implements PatentStatusService {

    @Resource
    PatentStatusMapper patentStatusMapper;
    @Resource
    private RedisUtil.redisList redisList;

    @Autowired
    private RedisUtil redisUtil;
    private Result rs;
    @Override
    public Result addPatentStatus(String ps_name) {

        try {
            PatentStatus patentStatus = patentStatusMapper.selectPatentStatusByName(ps_name);
            if (patentStatus==null){
                patentStatusMapper.addPatentStatus(ps_name);
                redisUtil.del("patentStatuses");
                rs = new Result("200","插入字典数据",null);
            }else{
                rs = new Result("400","该字典数据已经存在，切勿重复插入",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delPatentStatus(Integer ps_id) {
        try {
            patentStatusMapper.delPatentStatus(ps_id);
            redisUtil.del("patentStatuses");
            rs = new Result("200","删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findAllPatentStatus() {
        try {
            List patentStatuses = new ArrayList();

            if (redisUtil.hasKey("patentStatuses")) {
                log.warn("从redis中获取数据.");
                patentStatuses = redisList.get("patentStatuses", 0, -1);
            } else {
                patentStatuses = patentStatusMapper.findAllPatentStatus();
                log.warn("从数据库中获取数据.");
                log.warn("将数据存入redis...");
                redisList.set("patentStatuses", patentStatuses);
                log.info("成功存入redis.");
            }
            rs = new Result("200",null,patentStatuses);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result updatePatentRange(PatentStatus patentStatus) {
        try {
            if (patentStatus.getPs_name().equals("")){
                rs =new Result("400","更改数据不能为空",null);
            }else{
                patentStatusMapper.updatePatentRange(patentStatus);
                redisUtil.del("patentStatuses");
                rs =new Result("200","更改成功",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
