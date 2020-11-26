package com.sicnu.service.impl;

import com.sicnu.mapper.PeriodicalMapper;
import com.sicnu.pojo.Periodical;
import com.sicnu.service.PeriodicalService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PeriodicalServiceImpl implements PeriodicalService {
    @Resource
    PeriodicalMapper periodicalMapper;
    private Result rs;
    @Override
    public Result addPeriodical(String pt_name) {
        try {
            Periodical periodical = periodicalMapper.selectPeriodicalByName(pt_name);
            if (periodical!=null){
                rs = new Result("401","该字典已经存在",null);
            }else {
                periodicalMapper.addPeriodical(pt_name);
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
            rs = new Result("200","删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findAllPeriodical() {
        try {
            List<Periodical> periodicals = periodicalMapper.findAllPeriodical();
            rs = new Result("200",null,periodicals);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
