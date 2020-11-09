package com.sicnu.service.impl;

import com.sicnu.mapper.UserMapper;
import com.sicnu.pojo.User;
import com.sicnu.service.UserService;
import com.sicnu.util.MD5Util;
import com.sicnu.util.Result;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserSerciceImpl implements UserService {
    @Resource
    UserMapper userDao;
    @Resource
    JavaMailSenderImpl mailSender;
    @Override
    public Result findByName(String user_act,String user_pwd) {
        Result rs;
        String md5Password=MD5Util.md5(user_pwd);
        User user = userDao.findByName(user_act);
        if(user ==null){
            rs = new Result("1","用户名不存在",null);
            return rs;
        }

        if (user.getUser_state()==1){
            rs = new Result("1","该用户名被封禁",null);
            return rs;
        }
        if(!user.getUser_pwd().equals(md5Password)){
            rs = new Result("3","密码错误",null);
            return rs;
        }else{
            rs = new Result("200","登陆成功",null);
            return rs;
        }

    }

    @Override
    public  Result addUser( String user_act, String user_pwd, String user_name, String user_email, String user_number, String user_id_number, Integer user_state, Integer department_id, Integer role_id) throws MessagingException{
        Result rs = null;
        List<User> list = userDao.findAll();
        for (User value : list) {
            if (value.getUser_act().equals(user_act)) {
                rs = new Result("user_act", "该用户名已存在", null);
                return rs;
            } else if (value.getUser_number().equals(user_number)) {
                rs = new Result("user_number", "该学号已经注册过", null);
                return rs;
            } else if (value.getUser_id_number().equals(user_id_number)) {
                rs = new Result("user_id_number", "该身份证已经注册过", null);
                return rs;
            } else if (value.getUser_email().equals(user_email)) {
                rs = new Result("user_email", "该邮箱已经注册过", null);
                return rs;
            }
        }

        User user = new User();
        user.setUser_act(user_act);
        user.setUser_pwd(MD5Util.md5(user_pwd));
        user.setUser_name(user_name);
        user.setUser_email(user_email);
        user.setUser_number(user_number);
        user.setUser_id_number(user_id_number);
        user.setUser_state(user_state);
        user.setDepartment_id(department_id);
        user.setRole_id(role_id);
        userDao.addUser(user);
        rs = new Result("200", "用户注册成功", null);

        Integer userId = userDao.selectUserId(user_email);
        MimeMessage mailMessage =  mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage);
        helper.setSubject("高校科研管理系统注册验证码");
        helper.setText("<p>您已注册成功，您的用户名id为：<span style='color:blue;text-decoration:underline'>"+userId+"</span>,请勿遗忘或向他人泄露</p>",true);
        helper.setTo(user_email);
        helper.setFrom("1776557392@qq.com");
        mailSender.send(mailMessage);
        return rs;

    }

    @Override
    public Result updatePwd(String user_act, String user_pwd, String user_email, String user_number, String user_id_number) {
        User user =userDao.findByName(user_act);
        Result rs = null;
        if (user==null){
            rs = new Result("error","用户名不存在",null);
            return rs;
        }else if (!user.getUser_email().equals(user_email)){
            rs = new Result("user_email","邮箱验证未通过",null);
            return rs;
        }else if (!user.getUser_number().equals(user_number)){
            rs = new Result("user_number","学号验证未通过",null);
            return rs;
        }else if (!user.getUser_id_number().equals(user_id_number)){
            rs = new Result("user_id_number","身份证号验证未通过",null);
            return rs;
        }else{
            userDao.updatePwd(user_act, MD5Util.md5(user_pwd),user_email, user_number, user_id_number);
            rs = new Result("200","密码修改成功",null);
            return rs;
        }


    }

    @Override
    public Result changeStatus(Integer user_id, Integer user_state) {
        Result rs =null;

        userDao.changeStatus(user_id,user_state);

        return rs = new Result("200", "用户状态更改成功", null);
    }

    public Result loginOut(HttpSession session){
        Result rs =null;
        session.setAttribute("stoken", null);
        return rs = new Result("200", "用户退出成功", null);
    }

}
