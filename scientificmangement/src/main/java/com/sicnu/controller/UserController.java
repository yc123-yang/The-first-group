package com.sicnu.controller;


import com.sicnu.service.impl.UserServiceImpl;
import com.sicnu.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;


@RestController
@CrossOrigin
public class UserController {
    @Resource
    UserServiceImpl userService;

    private Result rs =null;

    //登录验证
    @RequestMapping("/user/login")
    public Result login(@RequestParam("user_act") String user_act, @RequestParam("user_pwd") String user_pwd, HttpSession session) {
        rs = userService.findByName(user_act,user_pwd);
        return rs;
    }

    @RequestMapping("/user/register")
    public Result addUser( String user_act, String user_pwd, String user_name, String user_email, String user_number, String user_id_number, Integer user_state, Integer department_id, Integer role_id) throws MessagingException{
        rs =userService.addUser(user_act, user_pwd, user_name, user_email, user_number, user_id_number, user_state, department_id, role_id);
        return rs;
    }

    @RequestMapping("/user/updatePwd")
    public Result updatePwd(String user_act, String user_pwd, String user_email, String user_number, String user_id_number){
        rs = userService.updatePwd(user_act, user_pwd, user_email, user_number, user_id_number);
        return rs;
    }

    @RequestMapping("/user/changeStatus")
    public Result changeStatus(Integer user_id, Integer user_state){
        rs = userService.changeStatus(user_id,user_state);
        return rs;
    }
    @RequestMapping("/user/loginOut")
    public Result loginOut(HttpSession session){
        rs =userService.loginOut(session);
        return rs;
    }

    @RequestMapping("/user/un_auth")
    public Result unAuth() {
        rs =new Result("404","用户未登录！",null);
        return rs;
    }

//    @ResponseBody
//    @RequestMapping("/user/unauthorized")
//    public Result unauthorized() {
//        rs =new Result("403","用户无权限",null);
//        return rs;
//    }
}