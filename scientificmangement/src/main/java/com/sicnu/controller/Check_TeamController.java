package com.sicnu.controller;

import com.sicnu.pojo.Check_Team;
import com.sicnu.service.impl.Check_TeamServiceImpl;
import com.sicnu.service.impl.Project_TeamServiceImpl;
import com.sicnu.util.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
public class Check_TeamController {

    @Resource
    Check_TeamServiceImpl check_teamService;
    @RequestMapping("/checkTeam/checkProjectTeam")
    public Result addProjectTeam(Integer project_id, Integer user_id, Date join_time) {
        Result rs =null;
        rs = check_teamService.checkProjectTeam(project_id, user_id, join_time);
        return rs ;
    }


    @RequestMapping("/checkTeam/selectCheckUser")
    public Result selectCheckUser(Integer project_id) {
        Result rs =null;
        rs = check_teamService.selectCheckUser(project_id);
        return rs;
    }
}
