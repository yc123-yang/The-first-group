package com.sicnu.controller;

import com.sicnu.mapper.LevelMapper;
import com.sicnu.pojo.Level;
import com.sicnu.service.LevelService;
import com.sicnu.service.impl.LevelServiceImpl;
import com.sicnu.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@CrossOrigin
@ResponseBody
public class LevelController {
    private Result rs;
    @Resource
    LevelServiceImpl levelService;

    @PostMapping("/level/addLevel")
    public Result addLevel(String level_name) {
        rs =levelService.addLevel(level_name);
        return rs;
    }

    @PostMapping("/level/delLevel")
    public Result delLevel(Integer level_id) {

        rs = levelService.delLevel(level_id);;
        return rs;
    }

    @PostMapping("/level/findAllLevel")
    public Result findAllLevel() {

        rs = levelService.findAllLevel();
        return rs;
    }
}
