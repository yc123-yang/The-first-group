package com.sicnu.controller;

import com.sicnu.service.Check_EmailService;
import com.sicnu.service.impl.Check_EmailServiceImpl;
import com.sicnu.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@RestController
@CrossOrigin
public class Check_EmailController {
    @Resource
    Check_EmailServiceImpl check_emailService;
    @RequestMapping("/user/addCode")
    public Result addCheckCode(@RequestParam("email")String email) throws MessagingException {
        Result rs =null;
        rs = check_emailService.addCheckCode(email);
        return rs;
    }
    @RequestMapping("/user/checkCode")
    public Result CheckCode(Integer check_code){
        Result rs =null;
        rs = check_emailService.findByCode(check_code);
        return rs;
    }
}
