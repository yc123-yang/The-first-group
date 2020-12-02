package com.sicnu.controller;

import com.sicnu.mapper.PaperTeamMapper;
import com.sicnu.pojo.teamMap.PaperTeamMap;
import com.sicnu.service.PaperTeamService;
import com.sicnu.service.impl.PaperTeamServiceImpl;
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
public class PaperTeamController {
    @Resource
    PaperTeamServiceImpl paperTeamService;

    private Result rs;
    @PostMapping("/team/addPaperTeamUser")
    public Result addPaperTeamUser(Integer paper_id, Integer user_id, String user_role, Double contribution, Integer department_id) {
        try {
            rs=paperTeamService.addPaperTeamUser(paper_id, user_id, user_role, contribution, department_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/team/delPaperTeamUser")
    public Result delPaperTeamUser(Integer user_id) {
        try {
            rs=paperTeamService.delPaperTeamUser(user_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/team/selectPaperTeam")
    public Result selectPaperTeam(Integer paper_id) {
        try {
            rs = paperTeamService.selectPaperTeam(paper_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
