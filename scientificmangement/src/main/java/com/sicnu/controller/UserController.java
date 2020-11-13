package com.sicnu.controller;


import com.sicnu.service.impl.UserServiceImpl;
import com.sicnu.util.Result;
import com.sicnu.util.TokenProccessor;




import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;


@RestController
@CrossOrigin
public class UserController {
    @Resource
    UserServiceImpl userService;
    //登录验证
    @RequestMapping("/user/login")
    public Result login(@RequestParam("user_act") String user_act, @RequestParam("user_pwd") String user_pwd, HttpSession session) {
       Result rs = userService.findByName(user_act,user_pwd);
        return rs;
    }
    @RequestMapping("/user/register")
    public Result addUser( String user_act, String user_pwd, String user_name, String user_email, String user_number, String user_id_number, Integer user_state, Integer department_id, Integer role_id) throws MessagingException{
        Result rs =null;
        rs =userService.addUser(user_act, user_pwd, user_name, user_email, user_number, user_id_number, user_state, department_id, role_id);
        return rs;
    }
    @RequestMapping("/user/updatePwd")
    public Result updatePwd(String user_act, String user_pwd, String user_email, String user_number, String user_id_number){
        Result rs =null;
        rs = userService.updatePwd(user_act, user_pwd, user_email, user_number, user_id_number);
        return rs;
    }
    @RequestMapping("/user/changeStatus")
    public Result changeStatus(Integer user_id, Integer user_state){
        Result rs =null;
        rs = userService.changeStatus(user_id,user_state);
        return rs;
    }
    @RequestMapping("/user/loginOut")
    public Result loginOut(HttpSession session){
        Result rs =userService.loginOut(session);
        return rs;
    }
}