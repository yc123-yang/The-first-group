package com.sicnu.controller;

import com.sicnu.pojo.AchievementType;
import com.sicnu.service.impl.AchievementTypeServiceImpl;
import com.sicnu.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@Controller
@ResponseBody
public class AchievementTypeController {

    @Resource
    AchievementTypeServiceImpl AchievementType;

    private Result rs ;
    @PostMapping("/achievementType/addAchievementType")
    public Result addAchievementType(String at_name) {

        rs= AchievementType.addAchievementType(at_name);
        return rs;
    }

    @PostMapping("/achievementType/delAchievementType")

    public Result delAchievementType(Integer at_id) {

        AchievementType.delAchievementType(at_id);
        rs =new Result("0","删除成功",null);

        return rs;
    }

    @PostMapping("/achievementType/findAllAchievementType")
    public Result findAllAchievementType() {
        rs = AchievementType.findAllAchievementType();
        return rs;
    }
}
