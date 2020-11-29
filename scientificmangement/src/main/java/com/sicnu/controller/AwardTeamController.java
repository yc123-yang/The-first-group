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
    public Result addAwardTeamUser(Integer award_id, Integer user_id, String user_role, Double contribution) {
        rs=awardTeamService.addAwardTeamUser(award_id, user_id, user_role, contribution);
        return rs;
    }

    @PostMapping("/team/delAwardTeamUser")

    public Result delAwardTeamUser(Integer user_id) {
        rs=awardTeamService.delAwardTeamUser(user_id);
        return rs;

    }

    @PostMapping("/team/selectAwardTeam")

    public Result selectAwardTeam(Integer award_id) {
        rs = awardTeamService.selectAwardTeam(award_id);
        return rs;
    }
}
