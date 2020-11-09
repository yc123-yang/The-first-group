package com.sicnu.controller;

import com.sicnu.pojo.Role;
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
        rs = roleService.addRole(role_name);
        return rs;
    }

    @PostMapping("/role/delRole")
    public Result delRole(Integer role_id) {
        rs = roleService.delRole(role_id);
        return rs;
    }

    @PostMapping("/role/findAllRole")
    public Result findAllRole() {
        rs = roleService.findAllRole();
        return rs;
    }
}
