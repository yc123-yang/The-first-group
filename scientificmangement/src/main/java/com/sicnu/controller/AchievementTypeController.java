package com.sicnu.controller;

import com.sicnu.service.impl.AchievementTypeServiceImpl;
import com.sicnu.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@CrossOrigin
@Controller
@ResponseBody
public class AchievementTypeController {

    @Resource
    AchievementTypeServiceImpl AchievementType;

    private Result rs;

    @PostMapping("/achievementType/addAchievementType")
    public Result addAchievementType(String at_name) {

        try {
            rs = AchievementType.addAchievementType(at_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/achievementType/delAchievementType")

    public Result delAchievementType(Integer at_id) {

        try {
            AchievementType.delAchievementType(at_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        rs = new Result("0", "删除成功", null);

        return rs;
    }

    @PostMapping("/achievementType/findAllAchievementType")
    public Result findAllAchievementType() {
        try {
            rs = AchievementType.findAllAchievementType();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
