package com.sicnu.controller;

import com.sicnu.pojo.Auth;
import com.sicnu.pojo.User;
import com.sicnu.service.impl.AuthServiceImpl;
import com.sicnu.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@Controller
@ResponseBody
public class AuthController {
    @Resource
    AuthServiceImpl authService;

    private Result rs = null;

    @PostMapping("/auth/addAuth")
    public Result addAuth(String auth_name) {

        try {
            rs = authService.addAuth(auth_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/auth/delAuth")
    public Result delAuth(Integer auth_id) {
        try {
            rs = authService.delAuth(auth_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/auth/findAllAuth")
    public Result findAllAuth() {
        try {
            rs = authService.findAllAuth();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/auth/getAuth")
    public List<Object> getAuth(Integer role_id) {
        List<Object> authList = null;
        try {
            authList = new ArrayList<>();
            authList = authService.getAuth(role_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authList;
    }

    @PostMapping("/auth/findAuthByPid")
    public Result findAuthByPid(HttpSession session, Integer auth_pid) {
        try {
            User user = (User) session.getAttribute("user");
            Integer user_id = user.getUser_id();
            rs = authService.findAuthByPid(user_id, auth_pid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
