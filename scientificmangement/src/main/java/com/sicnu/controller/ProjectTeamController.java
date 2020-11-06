package com.sicnu.controller;

import com.sicnu.service.impl.ProjectTeamServiceImpl;
import com.sicnu.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.lang.reflect.Method;

@Controller
@ResponseBody
public class ProjectTeamController {
    @Resource
    ProjectTeamServiceImpl projectTeamService;
    @RequestMapping(value = "/team/addTeamUser",method = RequestMethod.POST)
    public Result addTeamUser(Integer project_id, String user_name, String user_role, Integer role_id, Integer department_id) {
        Result rs = null;
        rs =projectTeamService.addTeamUser(project_id, user_name, user_role, role_id, department_id);
         return rs;
    }
    @PostMapping(value = "/team/delTeamUser")
    public Result delTeamUser(Integer user_id) {
        Result rs = null;
        rs = projectTeamService.delTeamUser(user_id);
        return rs;
    }
}
