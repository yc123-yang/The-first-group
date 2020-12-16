package com.sicnu.controller;

import com.sicnu.pojo.Project;
import com.sicnu.pojo.ProjectExamine;
import com.sicnu.pojo.teamMap.ProjectTeamMap;
import com.sicnu.service.impl.ProjectServiceImpl;
import com.sicnu.util.Result;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@ResponseBody
public class ProjectController {

    @Resource
    ProjectServiceImpl projectService;

    private Result rs;

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {

        //转换日期
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }

    /**
     * 审核项目
     *
     * @param project      前端传来项目信息
     * @param checkMessage 审核结果反馈
     * @param message      审核为通过反馈的原因
     * @return
     * @throws MessagingException
     */

    @PostMapping(value = "/project/addProject")

    public Result addProject(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Project project, String checkMessage, String message) throws MessagingException {
        try {
            rs = projectService.addProject(project, checkMessage, message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 根据前端传来的条件查询 项目信息
     *
     * @param project 前端传到后台的查询信息
     * @return@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
     */
    @RequestMapping(value = "/project/selectProjectByCondition", method = RequestMethod.POST)
    public Result selectProjectByCondition(Project project, String start_time_start, String start_time_end, String complete_time_start, String complete_time_end, String plan_time_start, String plan_time_end, Integer pageNum, Integer pageSize) throws Exception {
        try {
            rs = projectService.selectProjectByCondition(project, start_time_start, start_time_end, complete_time_start, complete_time_end, plan_time_start, plan_time_end, pageNum, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 编辑项目信息
     *
     * @param project 前端传到后台的修改信息
     * @return
     */

    @RequestMapping(value = "/project/updateProject", method = RequestMethod.POST)
    public Result updateProject(Project project) {
        try {
            rs = projectService.updateProject(project);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 项目组成员信息
     *
     * @param project_id 项目id
     * @return
     */
    @RequestMapping(value = "/project/delProject", method = RequestMethod.POST)
    public Result delProject(Integer project_id) {
        try {
            rs = projectService.delProject(project_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @RequestMapping(value = "/project/selectTeamByPid", method = RequestMethod.POST)
    public Result selectTeamByPid(Integer project_Id) {
        try {
            rs = projectService.selectTeamByPid(project_Id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;

    }


    /**
     * 根据条件获取所有项目
     *
     * @param project             项目筛选条件
     * @param start_time_start
     * @param start_time_end
     * @param complete_time_start
     * @param complete_time_end
     * @param plan_time_start
     * @param plan_time_end
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/project/selectAllProjectByCondition", method = RequestMethod.POST)
    public Result selectAllProjectByCondition(Project project, String start_time_start, String start_time_end, String complete_time_start, String complete_time_end, String plan_time_start, String plan_time_end, Integer pageNum, Integer pageSize) throws Exception {
        System.out.println("pageSize=" + pageSize);
        System.out.println(start_time_start);
        try {
            rs = projectService.selectAllProjectByCondition(project, start_time_start, start_time_end, complete_time_start, complete_time_end, plan_time_start, plan_time_end, pageNum, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 根据项目id获取项目信息
     *
     * @param project_id
     * @return
     */
    @PostMapping("/project/findProjectById")
    public Result findProjectById(Integer project_id) {
        try {
            rs = projectService.findProjectById(project_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/project/findPersonalProjectMessage")
    public  Result findPersonalProjectMessage(Integer project_id){
        try {

            rs = projectService.findPersonalProjectMessage(project_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
