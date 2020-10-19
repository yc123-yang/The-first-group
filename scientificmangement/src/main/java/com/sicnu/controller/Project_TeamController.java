package com.sicnu.controller;

import com.sicnu.service.impl.ProjectServiceImpl;
import com.sicnu.service.impl.Project_TeamServiceImpl;
import com.sicnu.util.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
public class Project_TeamController {

    @Resource
    Project_TeamServiceImpl projectTeamService;
    @RequestMapping("/team/addProjectTeam")
    public Result addProjectTeam(Integer project_id, Integer user_id, Date join_time) {
        Result rs =null;
        rs = projectTeamService.addProjectTeam(project_id, user_id, join_time);
        return rs = new Result("0","添加成功",null);
    }

    @RequestMapping("/team/delProjectTeam")
    public Result delProjectTeam(Integer project_id) {
        Result rs =null;
        rs = projectTeamService.delProjectTeam(project_id);
        return rs = new Result("0","删除成功",null);
    }

    @RequestMapping("/team/delTeamUser")

    public Result delTeamUser(Integer user_id) {
        Result rs =null;
        projectTeamService.delTeamUser(user_id);
        return rs = new Result("0","删除成功",null);
    }

}
