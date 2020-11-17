package com.sicnu.controller;

import com.sicnu.pojo.Project;
import com.sicnu.service.impl.ProjectServiceImpl;
import com.sicnu.util.Result;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.text.ParseException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Controller
@CrossOrigin
@ResponseBody
public class ProjectController {

    @Resource
    ProjectServiceImpl projectService;

    private  Result rs;

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {

        //转换日期
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }
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
        rs = projectService.addProject(project,checkMessage,message);
        return rs;
    }
    /**
     * 根据前端传来的条件查询 项目信息
     * @param project 前端传到后台的查询信息
     * @return@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
     */
    @RequestMapping(value = "/project/selectProjectByCondition",method = RequestMethod.POST)
    public Result selectProject( Project project,String start_time_start,String start_time_end, String complete_time_start,String complete_time_end,String plan_time_start,String plan_time_end,Integer pageNum,Integer pageSize) throws Exception {
        rs = projectService.selectProjectByCondition(project, start_time_start, start_time_end, complete_time_start, complete_time_end, plan_time_start, plan_time_end, pageNum, pageSize);
        return  rs;
    }
    /**
     * 编辑项目信息
     * @param project 前端传到后台的修改信息
     * @return
     */

    @RequestMapping(value = "/project/updateProject",method = RequestMethod.POST)
    public Result updateProject(@RequestBody Project project){
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
        rs = projectService.delProject(project_id);
        return  rs;
    }

    @RequestMapping(value = "/project/selectTeamByPid",method = RequestMethod.POST)
    public Result selectTeamByPid(Integer project_Id) {
        rs = projectService.selectTeamByPid(project_Id);
        return rs;

    }

    @PostMapping("/project/findAllProject")
    public Result findAllProject() {
        rs = projectService.findAllProject();
        return rs;
    }

    @RequestMapping(value = "/project/selectAllProjectByCondition",method = RequestMethod.POST)
    public Result selectAllProjectByCondition( Project project,String start_time_start,String start_time_end, String complete_time_start,String complete_time_end,String plan_time_start,String plan_time_end, Integer pageNum, Integer pageSize) throws Exception {
        System.out.println("pageSize="+pageSize);
        System.out.println(start_time_start);
        rs = projectService.selectAllProjectByCondition(project, start_time_start, start_time_end, complete_time_start, complete_time_end, plan_time_start, plan_time_end, pageNum, pageSize);
        return  rs;
    }

}
