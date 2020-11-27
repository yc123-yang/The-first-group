package com.sicnu.controller;

import com.sicnu.service.impl.ConclusionTypeServiceImpl;
import com.sicnu.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@CrossOrigin
@ResponseBody
public class ConclusionTypeController {
    @Resource
    ConclusionTypeServiceImpl conclusionTypeService;

    private Result rs;

    @PostMapping("/conclusionType/addConclusionType")
    @RequiresPermissions("/data")
    public Result addConclusionType(String ct_name) {

        try {
            rs = conclusionTypeService.addConclusionType(ct_name);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    @PostMapping("/conclusionType/delConclusionType")
    @RequiresPermissions("/data")
    public Result delConclusionType(Integer ct_id) {

        try {
            rs = conclusionTypeService.delConclusionType(ct_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/conclusionType/findAllConclusionType")
    @RequiresPermissions("/data")
    public Result findAllConclusionType() {
        try {
            rs = conclusionTypeService.findAllConclusionType();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
