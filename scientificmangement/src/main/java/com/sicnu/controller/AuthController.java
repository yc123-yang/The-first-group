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

        rs = authService.addAuth(auth_name);
        return rs;
    }

    @PostMapping("/auth/delAuth")
    public Result delAuth(Integer auth_id) {
        rs = authService.delAuth(auth_id);
        return rs;
    }

    @PostMapping("/auth/findAllAuth")
    public Result findAllAuth() {
        rs = authService.findAllAuth();
        return rs;
    }

    @PostMapping("/auth/getAuth")
    public List<Object> getAuth(Integer role_id) {
        List<Object> authList = new ArrayList<>();
        authList = authService.getAuth(role_id);
        return authList;
    }

    @PostMapping("/auth/findAuthByPid")
    public Result findAuthByPid(HttpSession session, Integer auth_pid) {
        User user = (User) session.getAttribute("user");
        Integer user_id = user.getUser_id();
        rs = authService.findAuthByPid(user_id, auth_pid);
        return rs;
    }
}
