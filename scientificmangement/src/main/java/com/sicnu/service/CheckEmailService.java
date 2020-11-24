package com.sicnu.service;

import com.sicnu.util.Result;

import javax.mail.MessagingException;

public interface CheckEmailService {
    Result addCheckCode(String email) throws MessagingException;

    Result findByCode(Integer check_code);

}
