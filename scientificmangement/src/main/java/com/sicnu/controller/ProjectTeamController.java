package com.sicnu.controller;

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
    public Result addTeamUser(Integer project_id, String user_name, String user_role, String team_role,Integer role_id, Integer department_id) {
        try {
            rs = projectTeamService.addProjectTeamUser(project_id, user_name, user_role, team_role, role_id, department_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping(value = "/team/delTeamUser")
    public Result delTeamUser(Integer user_id) {
        try {
            rs = projectTeamService.delProjectTeamUser(user_id);
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
}
