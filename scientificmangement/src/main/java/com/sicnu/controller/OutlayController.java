package com.sicnu.controller;

import com.sicnu.mapper.OutlayMapper;
import com.sicnu.pojo.Outlay;
import com.sicnu.service.OutlayService;
import com.sicnu.service.impl.OutlayServiceImpl;
import com.sicnu.util.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
public class OutlayController {
    @Resource
    private OutlayServiceImpl outlayService;
    private Result rs;
    @PostMapping("/outlay/addOutlay")
    public Result addOutlay(Outlay outlay) {
        try {
            rs=outlayService.addOutlay(outlay);
           } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/outlay/selectAllOutlay")
    public Result selectAllOutlay() {
        try {
            rs = outlayService.selectAllOutlay();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/outlay/delOutlayById")
    public Result delOutlayById(Integer outlay_id) {
        try {
            rs = outlayService.delOutlayById(outlay_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/outlay/updateOutlay")
    public Result updateOutlay(Outlay outlay) {
        try {
            rs =outlayService.updateOutlay(outlay);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
