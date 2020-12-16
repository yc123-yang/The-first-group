package com.sicnu.service.impl;

import com.sicnu.component.RedisUtil;
import com.sicnu.mapper.DepartmentMapper;
import com.sicnu.mapper.DepartmentRankMapper;
import com.sicnu.pojo.DepartmentRank;
import com.sicnu.service.DepartmentRankService;
import com.sicnu.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DepartmentRankServiceImpl implements DepartmentRankService {
    @Resource
    DepartmentRankMapper departmentRankMapper;

    @Resource
    private RedisUtil.redisList redisList;

    @Autowired
    private RedisUtil redisUtil;
    private Result rs;
    @Override
    public Result addDepartmentRank(String dr_name) {

        try {
            DepartmentRank departmentRank = departmentRankMapper.selectDepartmentRankByName(dr_name);
            if (departmentRank==null){
                departmentRankMapper.addDepartmentRank(dr_name);
                redisUtil.del("departmentRanks");
                rs = new Result("200","字典数据插入成功",null);
            }else{
                rs = new Result("200","字典数据已经存在，请勿重复插入",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delDepartmentRank(Integer dr_id) {
        try {
            departmentRankMapper.delDepartmentRank(dr_id);
            redisUtil.del("departmentRanks");
            rs = new Result("200","删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findAllDepartmentRank() {
        try {
            List departmentRanks = new ArrayList<>();
            if (redisUtil.hasKey("departmentRanks")) {
                log.warn("从redis中获取数据.");
                departmentRanks = redisList.get("departmentRanks", 0, -1);
            } else {
                departmentRanks = departmentRankMapper.findAllDepartmentRank();
                log.warn("从数据库中获取数据.");
                log.warn("将数据存入redis...");
                redisList.set("departmentRanks", departmentRanks);
                log.info("成功存入redis.");
            }
            rs = new Result("200",null,departmentRanks);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }



    @Override
    public Result updateDepartmentRank(DepartmentRank departmentRank) {
        try {
            if (departmentRank.getDr_name().equals("")){
                rs = new Result("400","更改的字典数据不能为空",null);
            }else{
                departmentRankMapper.updateDepartmentRank(departmentRank);
                redisUtil.del("departmentRanks");
                rs = new Result("200","更改成功",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;

    }
}
