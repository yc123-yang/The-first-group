package com.sicnu.controller;

import com.sicnu.service.impl.Check_ProjectServiceImpl;
import com.sicnu.service.impl.ProjectServiceImpl;
import com.sicnu.util.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
public class Check_ProjectController {
    @Resource
    Check_ProjectServiceImpl check_projectService;



    @RequestMapping("/project/checkProject")

    public Result checkProject(String project_stage, String project_type, Integer leader_id, String project_name, String project_abstract, String declaration, Date apply_time){
        Result rs =null;
        rs =check_projectService.checkProject(project_stage, project_type, leader_id, project_name, project_abstract, declaration, apply_time);
        return  rs;
    }


    @RequestMapping("/checkProject/findAllProject")
    public Result findAllProject() {
        Result rs = null;
        rs = check_projectService.findAllProject();
        return rs;
    }


}