package com.sicnu.controller;


import com.sicnu.service.impl.UserServiceImpl;
import com.sicnu.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

/**
 * 用户信息操作 连接
 */
@RestController
@CrossOrigin
public class UserController {
    @Resource
    UserServiceImpl userService;

    private Result rs =null;

    /**
     * 用户登录
      * @param user_act
     * @param user_pwd
     * @param session
     * @return
     */
    @RequestMapping("/user/login")
    public Result login(@RequestParam("user_act") String user_act, @RequestParam("user_pwd") String user_pwd, HttpSession session) {
        rs = userService.findByUserAct(user_act,user_pwd);
        return rs;
    }

    /**
     * 用户注册
     * @param user_act
     * @param user_pwd
     * @param user_name
     * @param user_email
     * @param user_number
     * @param user_id_number
     * @param user_state
     * @param department_id
     * @param role_id
     * @return
     * @throws MessagingException
     */
    @RequestMapping("/user/register")
    public Result addUser( String user_act, String user_pwd, String user_name, String user_email, String user_number, String user_id_number, Integer user_state, Integer department_id, Integer role_id) throws MessagingException{
        rs =userService.addUser(user_act, user_pwd, user_name, user_email, user_number, user_id_number, user_state, department_id, role_id);
        return rs;
    }

    /**
     * 用户修改密码
     * @param user_act
     * @param user_pwd
     * @param user_email
     * @param user_number
     * @param user_id_number
     * @return
     */
    @RequestMapping("/user/updatePwd")
    public Result updatePwd(String user_act, String user_pwd, String user_email, String user_number, String user_id_number){
        rs = userService.updatePwd(user_act, user_pwd, user_email, user_number, user_id_number);
        return rs;
    }

    /**
     * 用户账号状态更改
     * @param user_id
     * @param user_state
     * @return
     */
    @RequestMapping("/user/changeStatus")
    public Result changeStatus(Integer user_id, Integer user_state){
        rs = userService.changeStatus(user_id,user_state);
        return rs;
    }

    /**
     * 用户退出登录
     * @param session
     * @return
     */
    @RequestMapping("/user/loginOut")
    public Result loginOut(HttpSession session){
        rs =userService.loginOut(session);
        return rs;
    }

    /**
     * 用户没有登录
     * @return
     */
    @RequestMapping("/user/un_auth")
    public Result unAuth() {
        rs =new Result("404","用户未登录！",null);
        return rs;
    }

    /**
     * 用户无权限
     * @return
     */
//    @ResponseBody
//    @RequestMapping("/user/unauthorized")
//    public Result unauthorized() {
//        rs =new Result("403","用户无权限",null);
//        return rs;
//    }
}