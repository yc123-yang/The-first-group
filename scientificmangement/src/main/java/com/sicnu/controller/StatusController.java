package com.sicnu.controller;

import com.sicnu.service.impl.StatusServiceImpl;
import com.sicnu.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@CrossOrigin
@Controller
@ResponseBody
public class StatusController {

    @Resource
    StatusServiceImpl statusService;
    private Result rs;
    @PostMapping("/status/addStatus")
    public Result addStatus(String status_name) {


        rs = statusService.addStatus(status_name);
        return rs;
    }

    @PostMapping("/status/delStatus")
    public Result delStatus(Integer status_id) {
        rs =statusService.delStatus(status_id);
        return rs;
    }

    @PostMapping("/status/findAllStatus")
    public Result findAllStatus() {
        rs = statusService.findAllStatus();
        return rs;
    }
}
