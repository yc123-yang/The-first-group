package com.sicnu.controller;

import com.sicnu.pojo.PressLevel;
import com.sicnu.service.impl.PressLevelServiceImpl;
import com.sicnu.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@Controller
@ResponseBody
public class PressLevelController {

    @Resource
    PressLevelServiceImpl pressLevelService;

    private Result rs;

    @PostMapping("/pressLevel/addPressLevel")
    @RequiresPermissions("/data")
    public Result addPressLevel(String pl_name) {
        try {
            rs = pressLevelService.addPressLevel(pl_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/pressLevel/delPressLevel")
    @RequiresPermissions("/data")
    public Result delPressLevel(Integer pl_id) {
        try {
           rs = pressLevelService.delPressLevel(pl_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/pressLevel/findAllPressLevel")
    @RequiresPermissions("/data")
    public Result findAllPressLevel() {
        try {
            rs = pressLevelService.findAllPressLevel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/pressLevel/updatePressLevel")
    @RequiresPermissions("/data")
    public Result updatePressLevel(PressLevel pressLevel) {
        try {
            rs=pressLevelService.updatePressLevel(pressLevel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
