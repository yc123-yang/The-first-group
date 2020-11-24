package com.sicnu.controller;

import com.sicnu.util.Result;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 */
@ControllerAdvice
public class NoPermissionException {
    @ResponseBody
    @ExceptionHandler(UnauthorizedException.class)
    public Result handleShiroException(Exception ex) {
        Result rs = new Result("404", "无操作权限", null);
        return rs;
    }
//    @ResponseBody
//    @ExceptionHandler(AuthorizationException.class)
//    public String AuthorizationException(Exception ex) {
//        return "权限认证失败";
//    }
//@ExceptionHandler(value = UnauthorizedException.class)
//public ModelAndView myErrorHandler(UnauthorizedException ex) {
//    ModelAndView modelAndView = new ModelAndView();
//    //指定错误页面的模板页
//    modelAndView.setViewName("error");
//    modelAndView.addObject("code",ex.hashCode() );
//    modelAndView.addObject("msg",ex.getMessage());
//    return modelAndView;
//    }
}