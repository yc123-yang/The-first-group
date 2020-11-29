package com.sicnu.controller;

import com.sicnu.pojo.User;
import com.sicnu.service.CheckService;
import com.sicnu.service.impl.CheckServiceImpl;
import com.sicnu.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
@ResponseBody
public class CheckController {
    @Resource
    CheckServiceImpl checkService;

    private Result rs;

    @PostMapping("/check")
    public Result finalCheck() {
        rs = checkService.finalCheck();
        return rs;
    }
}
