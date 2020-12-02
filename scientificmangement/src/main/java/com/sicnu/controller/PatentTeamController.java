package com.sicnu.controller;


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
    public Result addPatentTeamUser(Integer patent_id, Integer user_id, String user_role, Double contribution,Integer department_id) {
        try {
            rs=patentTeamService.addPatentTeamUser(patent_id, user_id, user_role, contribution, department_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/team/delPatentTeamUser")
    public Result delPatentTeamUser(Integer user_id) {
        try {
            rs=patentTeamService.delPatentTeamUser(user_id);
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
}
