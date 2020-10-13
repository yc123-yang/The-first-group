package com.sicnu.controller;


import com.sicnu.service.impl.UserSerciceImpl;
import com.sicnu.util.Result;
import com.sicnu.util.TokenProccessor;




import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


@RestController
@CrossOrigin
public class UserController {
    @Resource
    UserSerciceImpl userSercice;
    //登录验证
    @RequestMapping("/user/login")
    public Result login(@RequestParam("username") String user_act, @RequestParam("password") String user_pwd, HttpSession session) {
       Result rs = userSercice.findByName(user_act,user_pwd);
        System.out.println(rs);
       if (rs.getStatus().equals("200")){
           String token = TokenProccessor.getInstance().makeToken();
           session.setAttribute("stoken", token);
           rs.setData(token);
           return rs;
       }else{
           return rs;
       }

    }
    @RequestMapping("/user/register")
    public Result addUser(String user_act,String user_pwd ,String user_name,String email,String user_number,String user_id_number,Integer user_state,Integer user_power){
        Result rs =null;
        rs =userSercice.addUser(user_act, user_pwd, user_name, email, user_number, user_id_number, user_state, user_power);
        return rs;
    }
    @RequestMapping("/user/updatePwd")
    public Result updatePwd(String user_act, String user_pwd, String user_email, String user_number, String user_id_number){
        Result rs =null;
        rs = userSercice.updatePwd(user_act, user_pwd, user_email, user_number, user_id_number);
        return rs;
    }
    @RequestMapping("/user/changeStatus")
    public Result updatePwd(Integer user_id, Integer user_state){
        Result rs =null;
        rs = userSercice.changeStatus(user_id,user_state);
        return rs;
    }
    @RequestMapping("/user/loginOut")
    public Result loginOut(Integer user_id, Integer user_state,HttpSession session){
        Result rs =userSercice.loginOut(user_id, user_state, session);
        return rs;
    }
}