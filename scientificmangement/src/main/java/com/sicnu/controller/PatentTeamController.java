package com.sicnu.controller;


import com.sicnu.pojo.PatentTeam;
import com.sicnu.service.impl.PatentTeamServiceImpl;
import com.sicnu.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@CrossOrigin
@ResponseBody
public class PatentTeamController {
    @Resource
    PatentTeamServiceImpl patentTeamService;

    private Result rs;

    @PostMapping("/team/addPatentTeamUser")
    public Result addPatentTeamUser(Integer patent_id, Integer user_id, Integer contribution) {
        try {
            rs=patentTeamService.addPatentTeamUser(patent_id, user_id, contribution);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/team/delPatentTeamUser")
    public Result delPatentTeamUser(Integer patent_id,Integer user_id) {
        try {
            rs=patentTeamService.delPatentTeamUser(patent_id,user_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/team/selectPatentTeam")
    public Result selectPatentTeam(Integer patent_id) {
        try {
            rs= patentTeamService.selectPatentTeam(patent_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/team/updatePatentTeamUser")
    public Result updatePatentTeamUser(PatentTeam patentTeam) {
        try {
            rs=patentTeamService.updatePatentTeamUser(patentTeam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
