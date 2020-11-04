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

@Service
public class ProjectServiceImpl implements ProjectService {

    @Resource
    ProjectMapper projectDao;
    @Resource
    UserMapper userDao;
    @Resource
    JavaMailSenderImpl mailSender;
    @Override
    public Result addProject(Project project) throws MessagingException {


        Result rs = null;

        projectDao.addProject(project);
        Integer projectId = projectDao.selectProjectId(project.getLeader_id(),project.getProject_name());

        User user =  userDao.findUserById(project.getLeader_id());
        MimeMessage mailMessage =  mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage);
        helper.setSubject("高校科研管理系统注册验证码");
        helper.setText("<p>您的项目申报成功，项目编号为：<span style='color:blue;text-decoration:underline'>"+projectId+"</span>,请勿遗忘。</p>",true);
        helper.setTo(user.getUser_email());
        helper.setFrom("1776557392@qq.com");
        mailSender.send(mailMessage);

        return rs;
    }

    @Override
    public Result selectProject(Project project) {
        Result rs = null;
        projectDao.selectProject(project);
        return rs;
    }

    @Override
    public Result delProject(Integer project_id) {
        Result rs = null;
        projectDao.delProject(project_id);
        return null;
    }

    @Override
    public Result updateProject(Project project) {
        Result rs =null;
        projectDao.updateProject(project);
        return rs;
    }


}
