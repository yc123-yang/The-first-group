package com.sicnu.service.impl;

import com.sicnu.dao.UserDao;
import com.sicnu.pojo.User;
import com.sicnu.service.UserService;
import com.sicnu.util.MD5Util;
import com.sicnu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Service
public class UserSerciceImpl implements UserService {
    @Resource
    UserDao userDao;

    @Override
    public Result findByName(String user_act,String user_pwd) {
        Result rs;
        String md5Password=MD5Util.md5(user_pwd);

        User user = userDao.findByName(user_act);
        if (user.getUser_state()==1){
            rs = new Result("1","该用户名被封禁",null);
            return rs;
        }
        System.out.println(user);
        if (user==null){
            rs = new Result("2","用户名错误或不存在",null);
            return rs;
        }else if(!md5Password.equals(user.getUser_pwd())){
            rs = new Result("3","密码错误",null);
            return rs;
        }else{
            rs = new Result("200","登陆成功",null);
            return rs;
        }
    }

    @Override
    public Result addUser(String user_act, String user_pwd, String user_name, String user_email, String user_number, String user_id_number, Integer user_state, Integer user_power) {
        Result rs = null;
        List<User> list = userDao.findAll();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUser_act().equals(user_act)) {
                rs = new Result("user_act", "该用户名已存在", null);
                return rs;
            }else if (list.get(i).getUser_number().equals(user_number)){
                rs = new Result("user_number", "该学号已经注册过", null);
                return rs;
            }else if (list.get(i).getUser_id_number().equals(user_id_number)){
                rs = new Result("user_id_number", "该身份证已经注册过", null);
                return rs;
            }else if (list.get(i).getUser_email().equals(user_email)){
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
        user.setUser_power(user_power);
        userDao.addUser(user);
        rs = new Result("200", "用户添加成功", null);
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
        User user=userDao.findUserById(user_id);
        Integer state = user.getUser_state();
        if (state==0){
            userDao.changeStatus(user_id,1);
        }else{
            userDao.changeStatus(user_id,1);
        }
        return rs = new Result("200", "用户状态更改成功", null);
    }

    public Result loginOut(Integer user_id, Integer user_state,HttpSession session){
        Result rs =null;
        session.setAttribute("stoken", null);
        return rs = new Result("200", "用户退出成功", null);

    }

}
