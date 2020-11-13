package com.sicnu.service.impl;

import com.sicnu.component.CacheUser;
import com.sicnu.mapper.UserMapper;
import com.sicnu.pojo.User;
import com.sicnu.service.UserService;
import com.sicnu.util.MD5Util;
import com.sicnu.util.PasswordMd;
import com.sicnu.util.Result;
import lombok.extern.slf4j.Slf4j;
import com.sicnu.exception.LoginException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.BeanUtils;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import javax.servlet.http.HttpSession;
import java.util.List;


@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userDao;
    @Resource
    JavaMailSenderImpl mailSender;

    private Result rs = null;

    @Override
    public Result findByName(String user_act,String user_pwd) {
        // 获取Subject实例对象，用户实例
        Subject currentUser = SecurityUtils.getSubject();
        // 将用户名和密码封装到UsernamePasswordToken
        UsernamePasswordToken token = new UsernamePasswordToken(user_act, user_pwd);

        CacheUser cacheUser;


        try {
            // 传到 MyShiroRealm 类中的方法进行认证
            currentUser.login(token);
            // 构建缓存用户信息返回给前端
            User user = (User) currentUser.getPrincipals().getPrimaryPrincipal();
//            cacheUser = CacheUser.builder()
//                    .token(currentUser.getSession().getId().toString())
//                    .build();
//            BeanUtils.copyProperties(user, cacheUser);
//            log.warn("CacheUser is {}", cacheUser.toString());
            return rs = new Result("0","登录成功",null);

        } catch (UnknownAccountException e) {
            log.error("账户不存在异常：", e);
            throw new LoginException("账户不存在",e);
        } catch (IncorrectCredentialsException e) {
            log.error("凭据错误（密码错误）异常：", e);

            throw new LoginException("密码不正确!", e);
        } catch (AuthenticationException e) {
            log.error("身份验证异常:", e);
            throw new LoginException("用户验证失败!", e);
        }





//        Result rs;
//        String md5Password=MD5Util.md5(user_pwd);
//        User user = userDao.findByName(user_act);
//        if(user ==null){
//            rs = new Result("1","用户名不存在",null);
//            return rs;
//        }
//
//        if (user.getUser_state()==1){
//            rs = new Result("1","该用户名被封禁",null);
//            return rs;
//        }
//        if(!user.getUser_pwd().equals(md5Password)){
//            rs = new Result("3","密码错误",null);
//            return rs;
//        }else{
//            rs = new Result("200","登陆成功",null);
//            return rs;
//        }
    }

    @Override
    public  Result addUser( String user_act, String user_pwd, String user_name, String user_email, String user_number, String user_id_number, Integer user_state, Integer department_id, Integer role_id) throws MessagingException{
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
        user.setUser_pwd(user_pwd);
        user.setUser_name(user_name);
        user.setUser_email(user_email);
        user.setUser_number(user_number);
        user.setUser_id_number(user_id_number);
        user.setUser_state(user_state);
        user.setDepartment_id(department_id);
        user.setRole_id(role_id);

        //加密
        new PasswordMd().encryptPassword(user);
        user.setSalt(ByteSource.Util.bytes(user.getUser_act()).toString());
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

        userDao.changeStatus(user_id,user_state);

        return rs = new Result("200", "用户状态更改成功", null);
    }

    public Result loginOut(HttpSession session){
        session.setAttribute("stoken", null);
        return rs = new Result("200", "用户退出成功", null);
    }

}
