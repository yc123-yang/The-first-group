package com.sicnu.controller;


import com.sicnu.service.impl.NatureServiceImpl;
import com.sicnu.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@CrossOrigin
@ResponseBody
public class NatureController {

    @Resource
    NatureServiceImpl natureService;
    private Result rs;

    @PostMapping("/nature/addNature")
    public Result addNature(String nature_name) {
        rs = natureService.addNature(nature_name);
        return rs;
    }

    @PostMapping("/nature/delNature")
    public Result delNature(Integer nature_id) {
        rs = natureService.delNature(nature_id);
        return rs;
    }

    @PostMapping("/nature/findAllNature")
    public Result findAllNature() {
        rs= natureService.findAllNature();
        return rs;
    }
}
