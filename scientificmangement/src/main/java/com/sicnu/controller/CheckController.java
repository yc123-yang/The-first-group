package com.sicnu.controller;

import com.sicnu.pojo.Check;
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

    @PostMapping("/check/selectCheckByCondition")
    public Result selectAllCheckByCondition(Integer user_id,Integer department_id,String check_time) {
        try {
            rs = checkService.selectAllCheckByCondition(user_id, department_id, check_time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/check/selectPersonalCheckByCondition")
    public Result selectPersonalCheckByCondition(Integer department_id,String check_time) {
        try {
            rs = checkService.selectPersonalCheckByCondition(department_id, check_time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
