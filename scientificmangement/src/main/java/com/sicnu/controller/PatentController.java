package com.sicnu.controller;

import com.sicnu.pojo.Patent;
import com.sicnu.service.impl.PatentServiceImpl;
import com.sicnu.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
@CrossOrigin
@ResponseBody
public class PatentController {
    @Resource
    PatentServiceImpl patentService;

    private Result rs;

    @PostMapping("/patent/addPatent")
    public Result addPatent(Patent patent,String checkMessage,String message) throws ParseException {
        try {
            rs = patentService.addPatent(patent, checkMessage, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/patent/selectPatentByCondition")
    public Result selectPatentByCondition(Patent patent, String application_time_start, String application_time_end, String public_time_start, String public_time_end, String authorization_time_start, String authorization_time_end, Integer pageSize, Integer pageNum) throws ParseException {
        System.out.println(patent);
        try {
            rs = patentService.selectPatentByCondition(patent, application_time_start, application_time_end, public_time_start, public_time_end, authorization_time_start, authorization_time_end, pageSize, pageNum);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/patent/updatePatent")
    public Result updatePatent(Patent patent) {
        try {
            rs = patentService.updatePatent(patent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/patent/delPatentById")
    public Result delPatentById(Integer patent_id) {
        try {
            rs = patentService.delPatentById(patent_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/patent/selectAllPatentByCondition")
    public Result selectAllPatentByCondition(Patent patent, String application_time_start, String application_time_end, String public_time_start, String public_time_end, String authorization_time_start, String authorization_time_end, Integer pageSize, Integer pageNum) throws ParseException {
        System.out.println(patent);
        try {
            rs = patentService.selectAllPatentByCondition(patent, application_time_start, application_time_end, public_time_start, public_time_end, authorization_time_start, authorization_time_end, pageSize, pageNum);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/patent/findPatentById")
    public Result findPatentById(Integer patent_id) {
        try {
            rs = patentService.findPatentById(patent_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
