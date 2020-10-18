package com.sicnu.controller;

import com.sicnu.service.impl.ProjectServiceImpl;
import com.sicnu.util.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
public class ProjectController {
    @Resource
    ProjectServiceImpl projectService;

    @RequestMapping("/project/selectProjectById")
    public Result selectProjectByName(String project_name) {
        Result rs = null;
        rs = projectService.selectProjectTeamByName(project_name);
        return rs;
    }

    @RequestMapping("/project/checkProject")

    public Result checkProject(String project_stage, String project_type, Integer leader_id, String project_name, String project_abstract, String declaration, Date apply_time){
        Result rs =null;
        rs =projectService.checkProject(project_stage, project_type, leader_id, project_name, project_abstract, declaration, apply_time);
        return  rs;
    }
    @RequestMapping("/project/addProject")
    public Result addProject(String status,String project_stage, String project_type, Integer leader_id, String project_name, String project_abstract, String declaration, Date apply_time) {
        Result rs = null;
        if (status.equals("suceess"))
        {
            rs = projectService.addProject(project_stage, project_type, leader_id, project_name, project_abstract, declaration, apply_time);
        }else {
            rs = new Result("1","审核未通过",null);
        }
        return rs;
    }

    @RequestMapping("/project/findAllProject")
    public Result findAllProject() {
        Result rs = null;
        rs = projectService.findAllProject();
        return rs;
    }

    @RequestMapping("/project/updateProjectStage")

    public Result updateProjectStage(String project_stage,Integer project_id,String project_name) {
        Result rs =null;
        rs = projectService.updateProjectStage(project_stage, project_id, project_name);
        return  rs ;
    }

    @RequestMapping("/project/updateProjectType")
    public Result updateProjectType(String project_type,Integer project_id,String project_name) {
        Result rs =projectService.updateProjectType(project_type, project_id, project_name);
        return  rs ;
    }

    @RequestMapping("/project/delProject")

    public Result delProject(Integer project_id) {
        Result rs = null;
        rs = projectService.delProject(project_id);
        return rs ;
    }

    @RequestMapping("/project/updateProject")

    public Result updateProject(Integer leader_id, String project_name, String project_stage, String project_type, Integer project_id) {
        Result rs =null;
        rs = projectService.updateProject(leader_id, project_name, project_stage, project_type, project_id);
        return  rs ;
    }
}