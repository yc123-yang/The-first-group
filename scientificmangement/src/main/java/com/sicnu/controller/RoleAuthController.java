package com.sicnu.controller;

import com.sicnu.service.impl.RoleAuthServiceImpl;
import com.sicnu.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
public class RoleAuthController {
    @Resource
    RoleAuthServiceImpl roleAuthService;
    private Result rs;

    @GetMapping("/roleAuth/updateAuth")

    public Result updateAuth(@RequestParam(value = "role_id") Integer role_id, @RequestParam(value = "authList") Integer[] authList) {
        try {
            rs = roleAuthService.updateAuth(role_id, authList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
