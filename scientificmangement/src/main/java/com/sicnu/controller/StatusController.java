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


        try {
            rs = statusService.addStatus(status_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/status/delStatus")
    public Result delStatus(Integer status_id) {
        try {
            rs = statusService.delStatus(status_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/status/findAllStatus")
    public Result findAllStatus() {
        try {
            rs = statusService.findAllStatus();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
