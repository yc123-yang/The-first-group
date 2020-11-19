package com.sicnu.service.impl;

import com.sicnu.pojo.CacheUser;
import com.sicnu.mapper.CacheUserMapper;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * user：使用系统的用户
 * 用户信息操作
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userDao;

    @Resource
    CacheUserMapper cacheUserMapper;

    @Resource
    JavaMailSenderImpl mailSender;

    @Resource
    AuthServiceImpl authService;

    private Result rs = null;

    /**
     * 用户登录
     * @param user_act：用户登录的账户
     * @param user_pwd：用户的登录密码
     * @return 反馈用户是否登录的情况
     */
    @Override
    public Result findByUserAct(String user_act,String user_pwd) {
        //删除上一个用户的缓存信息
        cacheUserMapper.delCacheUser();
        //获取用户的登录的账户密码
        User user1 = new User();
        user1.setUser_act(user_act);
        user1.setUser_pwd(user_pwd);
        //对用户账户加盐 密码加密
        new PasswordMd().encryptPassword(user1);

        //根据用户账户查找账户
        User user2 = userDao.findByUserAct(user_act);

        //对登录用户进行检验
        if (user2==null){
            rs = new Result("401","用户名不存在",null);
            return rs;
        }
        if (user2.getUser_state()==1){
            rs = new Result("402","该用户名被封禁",null);
            return rs;
        }
        if (!user1.getUser_pwd().equals(user2.getUser_pwd())){
            rs = new Result("403","密码错误",null);
            return rs;
        }else{
            // 获取Subject实例对象，用户实例
            Subject currentUser = SecurityUtils.getSubject();

            // 将用户名和密码封装到UsernamePasswordToken
            UsernamePasswordToken token = new UsernamePasswordToken(user_act, user_pwd);

            CacheUser cacheUser;
            try {
                // 传到 MyShiroRealm 类中的方法进行认证
                currentUser.login(token);

                // 构建缓存用户信息返回给前端，并保存到
                User user = (User) currentUser.getPrincipals().getPrimaryPrincipal();
                cacheUser = CacheUser.builder()
                        .token(currentUser.getSession().getId().toString())
                        .build();
                //复制登录用户的关键信息到缓存库
                BeanUtils.copyProperties(user, cacheUser);
                //吧用户信息添加到缓存库
                cacheUserMapper.addCacheUser(cacheUser);
                //输出日志
                log.warn("CacheUser is {}", cacheUser.toString());
                //获取用户角色权限 反馈给前端
                List<Object> auths = authService.getAuth(user2.getRole_id());
                List<Object> list = new ArrayList<>();
                Map<String,Object> map = new HashMap<>();
                map.put("userData",cacheUser);
                map.put("auth",auths);
                list.add(map);
                //反馈登录信息
                rs = new Result("200","登录成功",list);

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
            return rs;
        }

    }

    /**
     * 用户注册
     * @param user_act 用户名
     * @param user_pwd 密码
     * @param user_name 用户姓名
     * @param user_email 用户邮箱
     * @param user_number 用户个人学校编号
     * @param user_id_number 用户身份证
     * @param user_state 用户状态 默认是0 0是正常 1是封禁
     * @param department_id 用户部门id
     * @param role_id 用户角色 id 与 用户权限有关
     * @return
     * @throws MessagingException
     */
    @Override
    public  Result addUser( String user_act, String user_pwd, String user_name, String user_email, String user_number, String user_id_number, Integer user_state, Integer department_id, Integer role_id) throws MessagingException{
       //获取用户表所有用户
        List<User> list = userDao.findAllUser();
        //所有用户信息进行比对
        for (User value : list) {
            if (value.getUser_act().equals(user_act)) {
                rs = new Result("401", "该用户名已存在", null);
                return rs;
            } else if (value.getUser_number().equals(user_number)) {
                rs = new Result("402", "该学号已经注册过", null);
                return rs;
            } else if (value.getUser_id_number().equals(user_id_number)) {
                rs = new Result("403", "该身份证已经注册过", null);
                return rs;
            } else if (value.getUser_email().equals(user_email)) {
                rs = new Result("405", "该邮箱已经注册过", null);
                return rs;
            }
        }

        //若信息通过校验 就开始进行录入
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

        //对用户账号密码进行操作
        new PasswordMd().encryptPassword(user);
        user.setSalt(ByteSource.Util.bytes(user.getUser_act()).toString());
        //把用户信息注入
        userDao.addUser(user);
        rs = new Result("200", "用户注册成功", null);
        //获取用户id，通过邮箱方式反馈给用户
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

    /**
     * 对用户信息进行更新
     * @param user_act 用户账号
     * @param user_pwd 密码
     * @param user_email 邮箱
     * @param user_number 编号
     * @param user_id_number 用户身份证号
     * @return
     */

    @Override
    public Result updatePwd(String user_act, String user_pwd, String user_email, String user_number, String user_id_number) {
        //根据用户名获取用户信息
        User user =userDao.findByUserAct(user_act);

        //进行信息校验
        if (user==null){
            rs = new Result("400","用户名不存在",null);
            return rs;
        }else if (!user.getUser_email().equals(user_email)){
            rs = new Result("401","邮箱验证未通过",null);
            return rs;
        }else if (!user.getUser_number().equals(user_number)){
            rs = new Result("402","学号验证未通过",null);
            return rs;
        }else if (!user.getUser_id_number().equals(user_id_number)){
            rs = new Result("403","身份证号验证未通过",null);
            return rs;
        }else{
            userDao.updatePwd(user_act, MD5Util.md5(user_pwd),user_email, user_number, user_id_number);
            rs = new Result("200","密码修改成功",null);
            return rs;
        }


    }

    /**
     * 管理功能，对用户账号的封禁
     * @param user_id 用户id
     * @param user_state 用户账号状态
     * @return
     */
    @Override
    public Result changeStatus(Integer user_id, Integer user_state) {

        userDao.changeStatus(user_id,user_state);

        return rs = new Result("200", "用户状态更改成功", null);
    }

    /**
     * 退出登录
     */
    public Result loginOut(HttpSession session){
        Subject subject = SecurityUtils.getSubject();
        //shiro清空用户登录信息
        subject.logout();
        //清苦缓存
        cacheUserMapper.delCacheUser();
        return rs = new Result("200", "用户退出成功", null);
    }

}
