package com.sicnu.controller;

import com.sicnu.pojo.Project;
import com.sicnu.service.impl.ProjectServiceImpl;
import com.sicnu.util.Result;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@Controller
@CrossOrigin
@ResponseBody
public class ProjectController {

    @Resource
    ProjectServiceImpl projectService;

    /**
     * 审核项目
     * @param project 前端传来项目信息
     * @param checkMessage 审核结果反馈
     * @param message 审核为通过反馈的原因
     * @return
     * @throws MessagingException
     */
    @PostMapping(value = "/project/addProject")

    public Result addProject(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Project project, String checkMessage, String message) throws MessagingException {
        Result rs =null;
        rs = projectService.addProject(project,checkMessage,message);
        return rs;
    }
    /**
     * 根据前端传来的条件查询 项目信息
     * @param project 前端传到后台的查询信息
     * @return
     */
    @RequestMapping(value = "/project/selectProject",method = RequestMethod.POST)
    public Result selectProject(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Project project){
        Result rs =null;
        rs = projectService.selectProject(project);
        return  rs;
    }
    /**
     * 编辑项目信息
     * @param project 前端传到后台的修改信息
     * @return
     */

    @RequestMapping(value = "/project/updateProject",method = RequestMethod.POST)
    public Result updateProject(@RequestBody Project project){
        Result rs =null;
        rs = projectService.updateProject(project);
        return  rs;
    }
    /**
     * 项目组成员信息
     * @param project_id 项目id
     * @return
     */
    @RequestMapping(value = "/project/delProject",method = RequestMethod.POST)
    public Result delProject(Integer project_id){
        Result rs =null;
        rs = projectService.delProject(project_id);
        return  rs;
    }

    @RequestMapping(value = "/project/selectTeamByPid",method = RequestMethod.POST)
    public Result selectTeamByPid(Integer project_Id) {
        Result rs = null;
        rs = projectService.selectTeamByPid(project_Id);
        return rs;

    }

}
