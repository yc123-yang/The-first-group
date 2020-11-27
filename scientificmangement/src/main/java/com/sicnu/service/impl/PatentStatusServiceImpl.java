package com.sicnu.service.impl;

import com.sicnu.mapper.PatentStatusMapper;
import com.sicnu.pojo.PatentStatus;
import com.sicnu.service.PatentStatusService;
import com.sicnu.util.Result;

import javax.annotation.Resource;
import java.util.List;

public class PatentStatusServiceImpl implements PatentStatusService {

    @Resource
    PatentStatusMapper patentStatusMapper;

    private Result rs;
    @Override
    public Result addPatentStatus(String ps_name) {

        try {
            PatentStatus patentStatus = patentStatusMapper.selectPatentStatusByName(ps_name);
            if (patentStatus.equals("")){
                patentStatusMapper.addPatentStatus(ps_name);
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
            rs = new Result("200","删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findAllPatentStatus() {
        try {
            List<PatentStatus> patentStatuses = patentStatusMapper.findAllPatentStatus();
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
                rs =new Result("200","更改成功",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
