package com.sicnu.service.impl;

import com.sicnu.mapper.ProjectMapper;
import com.sicnu.mapper.UserMapper;
import com.sicnu.pojo.Project;
import com.sicnu.pojo.User;
import com.sicnu.service.ProjectService;
import com.sicnu.util.Result;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("ProjectServiceImpl")
public class ProjectServiceImpl implements ProjectService {

    @Resource
    ReviewProjectServiceImpl reviewProjectService;
    @Resource
    ProjectMapper projectDao;
    @Resource
    UserMapper userDao;
    @Resource
    JavaMailSenderImpl mailSender;

    /**
     * 审核项目
     * @param project 前端传来项目信息
     * @param checkMessage 审核结果反馈
     * @param message 审核为通过反馈的原因
     * @return
     * @throws MessagingException
     */
    @Override
    public Result addProject(Project project,String checkMessage,String message) throws MessagingException {

        System.out.println("调用");
        Integer projectId = projectDao.selectProjectId(project.getLeader_id(),project.getProject_name());
        User user =  userDao.findUserById(project.getLeader_id());
        MimeMessage mailMessage =  mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage);

        Result rs = null;
        if (checkMessage.equals("fail")){
            helper.setSubject("高校科研管理系统注册验证码");
            helper.setText("<p>您的项目申报审核未通过，因为<span style='color:blue;text-decoration:underline'>"+message+"</span>,请您解决之后重新申请。</p>",true);
            helper.setTo(user.getUser_email());
            helper.setFrom("1776557392@qq.com");
            mailSender.send(mailMessage);
            reviewProjectService.delReviewProject(projectId);
            rs = new Result("1","审核结果已反馈",null);
        }else{
            project.setStatus_id(2);
            projectDao.addProject(project);
            helper.setSubject("高校科研管理系统注册验证码");
            helper.setText("<p>您的项目申报审核成功，项目编号为：<span style='color:blue;text-decoration:underline'>"+projectId+"</span>,请勿遗忘。</p>",true);
            helper.setTo(user.getUser_email());
            helper.setFrom("1776557392@qq.com");
            mailSender.send(mailMessage);
            reviewProjectService.delReviewProject(projectId);
            rs = new Result("0","审核结果已反馈",null);
        }

        return rs;
    }

    /**
     * 根据前端传来的条件查询 项目信息
     * @param project 前端传到后台的查询信息
     * @return
     */
    @Override
    public Result selectProject(Project project) {
        Result rs = null;
        List<Project> projects = projectDao.selectProject(project);
        return rs = new Result("0",null,projects);
    }

    /**
     * 编辑项目信息
     * @param project 前端传到后台的修改信息
     * @return
     */

    @Override
    public Result updateProject(Project project) {
        Result rs =null;
        projectDao.updateProject(project);
        return rs = new Result("1","修改成功",null);

    }

    /**
     * 项目组成员信息
     * @param project_Id 项目id
     * @return
     */
    @Override
    public Result selectTeamByPid(Integer project_Id) {
        Result rs = null;
        List<Project> projects = projectDao.selectTeamByPid(2);
        List<Object> list = new ArrayList<>();

        for (Project project1 : projects) {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("name",project1.getProjectTeams().get(0).getUsers().get(0).getUser_name());
            map.put("role_id",project1.getProjectTeams().get(0).getUsers().get(0).getRole_id());
            map.put("user_status",project1.getProjectTeams().get(0).getUser_role());
            map.put("depart_id",project1.getProjectTeams().get(0).getUsers().get(0).getDepartment_id());
            list.add(map);

        }
        return rs = new Result("0","",list);
    }


    /**
     * 删除某个项目
     * @param project_id
     * @return
     */

    @Override
    public Result delProject(Integer project_id) {
        Result rs = null;
        projectDao.delProject(project_id);
        return rs = new Result("1","删除成功",null);
    }



}
