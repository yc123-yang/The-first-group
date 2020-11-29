package com.sicnu.controller;

import com.sicnu.mapper.PatentStatusMapper;
import com.sicnu.pojo.PatentStatus;
import com.sicnu.service.PatentStatusService;
import com.sicnu.service.impl.PatentStatusServiceImpl;
import com.sicnu.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
public class PatentStatusController {

    @Resource
    PatentStatusServiceImpl patentStatusService;

    private Result rs;

    @PostMapping("/patentStatus/addPatentStatus")
    @RequiresPermissions("/data")
    public Result addPatentStatus(String ps_name) {

        try {
            rs= patentStatusService.addPatentStatus(ps_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/patentStatus/delPatentStatus")
    @RequiresPermissions("/data")
    public Result delPatentStatus(Integer ps_id) {
        try {
            rs = patentStatusService.delPatentStatus(ps_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/patentStatus/findAllPatentStatus")
    public Result findAllPatentStatus() {
        try {
            rs = patentStatusService.findAllPatentStatus();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/patentStatus/updatePatentRange")
    @RequiresPermissions("/data")
    public Result updatePatentRange(PatentStatus patentStatus) {
        try {
            rs = patentStatusService.updatePatentRange(patentStatus);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
