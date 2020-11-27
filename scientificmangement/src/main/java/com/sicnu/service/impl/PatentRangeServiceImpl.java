package com.sicnu.service.impl;

import com.sicnu.mapper.PatentRangeMapper;
import com.sicnu.pojo.PatentRange;
import com.sicnu.pojo.ResearchType;
import com.sicnu.service.PatentRangeService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PatentRangeServiceImpl implements PatentRangeService {

    @Resource
    PatentRangeMapper patentRangeMapper;
    private Result rs;
    @Override
    public Result addPatentRange(String pr_name) {
        try {
            PatentRange patentRange = patentRangeMapper.selectPatentRangeByName(pr_name);
            if (patentRange.equals("")){
                patentRangeMapper.addPatentRange(pr_name);
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
            rs= new Result("200","删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findAllPatentRange() {
        try {
            List<PatentRange> patentRanges = patentRangeMapper.findAllPatentRange();
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
            rs= new Result("200","更改成功",null);
        }
        return rs;
    }
}
