package com.sicnu.service.impl;

import com.sicnu.dao.Check_EmailDao;
import com.sicnu.pojo.Check_Email;
import com.sicnu.service.Check_EmailService;
import com.sicnu.util.Code;
import com.sicnu.util.Result;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class Check_EmailServiceImpl implements Check_EmailService {
    private static final Logger logger =LoggerFactory.getLogger(Check_EmailServiceImpl.class);

    @Resource
    JavaMailSenderImpl mailSender;
    @Resource
    Check_EmailDao check_emailDao;
    @Resource
    Check_EmailServiceImpl check_emailService;
    @Override
    public Result addCheckCode(String email) throws MessagingException {
        Integer check_code;
        Date overtime;
        Result rs =null;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        Date overTime = new Date(date.getTime()+300000);
        check_code = new Code().smsCode();
        List<Check_Email> check_emails = check_emailDao.findAllCode();
        for (int i = 0; i <check_emails.size() ; i++) {
            if(check_emails.get(i).getCheck_code()==check_code){
                check_emailService.addCheckCode(email);
            }
        }

        check_emailDao.addCheckCode(email, check_code, overTime);
        rs = new Result("0","验证码发送成功",null);


        MimeMessage mailMessage =  mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage);
        helper.setSubject("高校科研管理系统注册验证码");
        helper.setText("<p>您的注册码为：<span style='color:blue;text-decoration:underline'>"+check_code+"</span>,该注册码将会在5分钟("+overTime+")后失效，请您尽快进注册验证！</p>",true);
        helper.setTo(email);
        helper.setFrom("1776557392@qq.com");
        mailSender.send(mailMessage);
        return rs;
    }

    @Override
    public Result findByCode(Integer check_code){
        Result rs =null;
        Check_Email check_email = check_emailDao.findByCode(check_code);
        Date endTime = check_email.getOvertime();

        Date date = new Date();
        Date startTime = new Date(date.getTime());

        if (startTime.after(endTime)){
            rs = new Result("1","验证码已经失效",null);
            return rs;
        }else{
            rs = new Result("0","验证码成功",null);
            return  rs;
        }

    }


}
