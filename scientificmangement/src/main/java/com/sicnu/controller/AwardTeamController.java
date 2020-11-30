package com.sicnu.controller;

import com.sicnu.service.impl.AwardTeamServiceImpl;
import com.sicnu.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@CrossOrigin
@Controller
@ResponseBody
public class AwardTeamController {

    @Resource
    AwardTeamServiceImpl awardTeamService;
    private Result rs;

    @PostMapping("/team/addAwardTeamUser")
    public Result addAwardTeamUser(Integer award_id,String user_name, String user_role, Double contribution, Integer department_id) {
        try {
            rs=awardTeamService.addAwardTeamUser(award_id, user_name, user_role, contribution, department_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/team/delAwardTeamUser")

    public Result delAwardTeamUser(Integer user_id) {
        try {
            rs=awardTeamService.delAwardTeamUser(user_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;

    }

    @PostMapping("/team/selectAwardTeam")

    public Result selectAwardTeam(Integer award_id) {
        try {
            rs = awardTeamService.selectAwardTeam(award_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
