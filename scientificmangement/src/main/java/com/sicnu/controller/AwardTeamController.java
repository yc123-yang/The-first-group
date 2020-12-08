package com.sicnu.controller;

import com.sicnu.pojo.Award;
import com.sicnu.pojo.AwardTeam;
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
    public Result addAwardTeamUser(Integer award_id,Integer user_id, Integer contribution) {
        try {
            rs=awardTeamService.addAwardTeamUser(award_id, user_id,contribution);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/team/delAwardTeamUser")

    public Result delAwardTeamUser(Integer award_id,Integer user_id) {
        try {
            rs=awardTeamService.delAwardTeamUser(award_id,user_id);
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

    @PostMapping("/team/updateAwardTeamUser")
    public Result updateAwardTeamUser(AwardTeam awardTeam) {
        try {
            rs=awardTeamService.updateAwardTeamUser(awardTeam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
