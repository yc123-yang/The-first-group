package com.sicnu.service.impl;

import com.sicnu.dao.ProjectDao;
import com.sicnu.dao.UserDao;
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
import java.util.Date;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Resource
    JavaMailSenderImpl mailSender;
    @Resource
    ProjectDao projectDao;
    @Resource
    UserDao userDao;
    @Resource
    CheckProjectServiceImpl check_projectService;
    @Override
    public Result selectProjectTeamByName(String project_name) {
        Result rs =null;
        List<Project> project = projectDao.selectProjectTeamByName(project_name);
        if (project!=null){
            rs = new Result("0","查找成功",project);
        }else{
            rs = new Result("0","项目未注册申报或已被删除",null);
        }
        return rs;
    }


    @Override
    public Result addProject(String project_stage, String project_type, Integer leader_id, String project_name, String project_abstract, String declaration, Date apply_time) throws MessagingException {
        Result rs = null;
        User user = userDao.findUserById(leader_id);
        Project project = projectDao.selectProjectByName(project_name,leader_id);
        if (project==null){
            projectDao.addProject(project_stage, project_type, leader_id, project_name, project_abstract, declaration, apply_time);
            rs = new Result("0","审核通过",null);
            Project project1 = projectDao.selectProjectByName(project_name,leader_id);
            check_projectService.delCheckProjectById(project1.getProject_id());

            MimeMessage mailMessage =  mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage);
            helper.setSubject("高校科研管理系统注册验证码");
            helper.setText("<p>您的项目申请成功通过审核，您的项目id为：<span style='color:blue;text-decoration:underline'>"+project1.getProject_id()+"</span></p>",true);
            helper.setTo(user.getUser_email());
            helper.setFrom("1776557392@qq.com");
            mailSender.send(mailMessage);
        }else{
            rs = new Result("0","同一个人不能申请同一个项目两次",null);
        }

        return rs;
    }

    @Override
    public Result findAllProject() {
        Result rs =null;
        List<Project> projects = projectDao.findAllProject();
        return  rs = new Result("0",null,projects);
    }


    @Override
    public Result updateProjectStage(String project_stage,Integer project_id,String project_name) {
        Result rs =null;
        projectDao.updateProjectStage(project_stage, project_id, project_name);
        return  rs = new Result("0","更改成功",null);


    }

    @Override
    public Result updateProjectType(String project_type,Integer project_id,String project_name) {
        Result rs =null;
        projectDao.updateProjectType(project_type, project_id, project_name);
        return  rs = new Result("0","更改成功",null);
    }

    @Override
    public Result delProject(Integer project_id,Integer leader_id,String project_name) {
        Result rs = null;
        projectDao.delProject(project_id, leader_id, project_name);
        return rs = new Result("0","删除成功",null);
    }

    @Override
    public Result updateProject(Integer leader_id, String project_name, String project_stage, String project_type, Integer project_id) {
        Result rs =null;
        projectDao.updateProject(leader_id, project_name, project_stage, project_type, project_id);
        return  rs = new Result("0","更改成功",null);
    }


}
