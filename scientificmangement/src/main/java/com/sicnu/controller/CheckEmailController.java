package com.sicnu.controller;

import com.sicnu.service.impl.CheckEmailServiceImpl;
import com.sicnu.util.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@RestController
@CrossOrigin
public class CheckEmailController {
    @Resource
    CheckEmailServiceImpl check_emailService;

    @RequestMapping("/user/addCode")
    public Result addCheckCode(@RequestParam("user_email") String user_email) throws MessagingException {
        Result rs = null;
        rs = check_emailService.addCheckCode(user_email);
        return rs;
    }

    @RequestMapping("/user/checkCode")
    public Result CheckCode(Integer check_code) {
        Result rs = null;
        rs = check_emailService.findByCode(check_code);
        return rs;
    }
}
