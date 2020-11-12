package com.sicnu.controller;

import com.sicnu.service.impl.ConclusionTypeServiceImpl;
import com.sicnu.util.Result;
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
    public Result addConclusionType(String ct_name) {

        rs = conclusionTypeService.addConclusionType(ct_name);

        return rs;
    }

    @PostMapping("/conclusionType/delConclusionType")
    public Result delConclusionType(Integer ct_id) {

        rs = conclusionTypeService.delConclusionType(ct_id);
        return rs;
    }

    @PostMapping("/conclusionType/findAllConclusionType")
    public Result findAllConclusionType() {
        rs = conclusionTypeService.findAllConclusionType();
        return rs;
    }
}
