package com.sicnu.component;

import com.sicnu.pojo.User;
import com.sicnu.service.impl.UserSerciceImpl;
import com.sicnu.util.Result;
import org.springframework.web.servlet.HandlerInterceptor;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


import com.alibaba.fastjson.JSON;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Resource
    UserSerciceImpl userSercice;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        Result rs = null;
        //登录验证
        System.out.println("前端token："+request.getHeader("token"));

        String header = request.getHeader("token");
        String attribute = (String)session.getAttribute("stoken");
        System.out.println("sessionToken："+attribute);


        if( header == null ||attribute == null ) {
            returnJson(response, JSON.toJSONString(rs =new Result("401","用户名不存在或你还未登录，请先登录",null)));
            return false;
        }else if(!attribute.equals(header.split(" ")[1])) {
            returnJson(response, JSON.toJSONString(rs =new Result("401","你还未登录或登录已过期，请先登录",null)));
            return false;
        }else {
            return true;
        }
    }
    //返回json数据
    private void returnJson(HttpServletResponse response, String json) throws Exception{
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);

        } catch (IOException e) {
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}

