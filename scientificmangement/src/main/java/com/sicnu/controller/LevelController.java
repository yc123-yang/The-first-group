package com.sicnu.controller;

import com.sicnu.pojo.Level;
import com.sicnu.service.impl.LevelServiceImpl;
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
public class LevelController {
    private Result rs;
    @Resource
    LevelServiceImpl levelService;

    @PostMapping("/level/addLevel")
    @RequiresPermissions("/data")
    public Result addLevel(String level_name) {
        try {
            rs = levelService.addLevel(level_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/level/delLevel")
    @RequiresPermissions("/data")
    public Result delLevel(Integer level_id) {

        try {
            rs = levelService.delLevel(level_id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    @PostMapping("/level/findAllLevel")
    public Result findAllLevel() {

        try {
            rs = levelService.findAllLevel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/level/updateLevel")
    @RequiresPermissions("/data")
    public Result updateLevel(Level level) {
        try {
            rs=levelService.updateLevel(level);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
