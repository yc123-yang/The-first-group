package com.sicnu.service;

import com.sicnu.pojo.User;
import com.sicnu.util.Result;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {
    Result findByName(String user_act,String user_pwd);
    Result addUser(String user_act,String user_pwd ,String user_name,String user_email,String user_number,String user_id_number,Integer user_state,Integer user_power) throws MessagingException;
    Result updatePwd(String user_act,String user_pwd,String user_email,String user_number,String user_id_number);
    Result changeStatus(Integer user_id,Integer user_state);
    Result loginOut(HttpSession session);
}
