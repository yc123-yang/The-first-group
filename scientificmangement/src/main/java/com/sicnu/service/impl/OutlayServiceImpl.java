package com.sicnu.service.impl;

import com.sicnu.mapper.OutlayMapper;
import com.sicnu.pojo.Outlay;
import com.sicnu.service.OutlayService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OutlayServiceImpl implements OutlayService {
    @Resource
    private OutlayMapper outlayMapper;
    private Result rs;
    @Override
    public Result addOutlay(Outlay outlay) {
        try {
            outlayMapper.addOutlay(outlay);
            rs = new Result("200","插入成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result selectAllOutlay() {
        try {
            List<Outlay> outlays = outlayMapper.selectAllOutlay();
            rs = new Result("200",null,outlays);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delOutlayById(Integer outlay_id) {
        try {
            outlayMapper.delOutlayById(outlay_id);
            rs = new Result("200","删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result updateOutlay(Outlay outlay) {
        try {
            outlayMapper.updateOutlay(outlay);
            rs = new Result("200","更改成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }


}
