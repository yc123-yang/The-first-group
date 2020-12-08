package com.sicnu.controller;

import com.sicnu.pojo.ProjectTeam;
import com.sicnu.service.impl.ProjectTeamServiceImpl;
import com.sicnu.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@ResponseBody
@CrossOrigin
public class ProjectTeamController {
    @Resource
    ProjectTeamServiceImpl projectTeamService;

    private Result rs = null;

    @RequestMapping(value = "/team/addTeamUser", method = RequestMethod.POST)
    public Result addTeamUser(Integer project_id, Integer user_id,  String team_role) {
        try {
            rs = projectTeamService.addProjectTeamUser(project_id, user_id, team_role);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping(value = "/team/delTeamUser")
    public Result delTeamUser(Integer project_id,Integer user_id) {
        try {
            rs = projectTeamService.delProjectTeamUser(project_id,user_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping(value = "/team/selectProjectTeam")
    public Result selectProjectTeam(Integer project_id) {
        rs = projectTeamService.selectProjectTeam(project_id);
        return rs;
    }

    @PostMapping(value = "/team/updateProjectTeamUser")
    public Result updateProjectTeamUser(ProjectTeam projectTeam) {
        try {
           rs= projectTeamService.updateProjectTeamUser(projectTeam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
