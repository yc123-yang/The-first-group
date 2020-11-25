package com.sicnu.controller;

import com.sicnu.service.impl.RoleServiceImpl;
import com.sicnu.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@CrossOrigin
@ResponseBody
public class RoleController {

    @Resource
    RoleServiceImpl roleService;
    private Result rs = null;

    @PostMapping("/role/addRole")
    public Result addRole(String role_name) {
        try {
            rs = roleService.addRole(role_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/role/delRole")
    public Result delRole(Integer role_id) {
        try {
            rs = roleService.delRole(role_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/role/findAllRole")
    public Result findAllRole() {
        try {
            rs = roleService.findAllRole();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
