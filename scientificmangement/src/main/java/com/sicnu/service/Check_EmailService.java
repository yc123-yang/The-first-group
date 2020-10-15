package com.sicnu.service;

import com.sicnu.pojo.Check_Email;
import com.sicnu.util.Result;

import javax.mail.MessagingException;
import java.util.Date;

public interface Check_EmailService {
    Result addCheckCode(String email) throws MessagingException;
    Result findByCode(Integer check_code);
}
