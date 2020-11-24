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
    public Result addPatent(Patent patent, String application_time1, String public_time1, String authorization_time1) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!application_time1.equals("")) {
            patent.setApplication_time((Date) sdf.parse(application_time1));
        }
        if (!public_time1.equals("")) {
            patent.setPublic_time((Date) sdf.parse(public_time1));
        }
        if (!authorization_time1.equals("")) {
            patent.setAuthorization_time((Date) sdf.parse(authorization_time1));
        }
        rs = patentService.addPatent(patent);
        return rs;
    }

    @PostMapping("/patent/selectPatentByCondition")
    public Result selectPatentByCondition(Patent patent, String application_time_start, String application_time_end, String public_time_start, String public_time_end, String authorization_time_start, String authorization_time_end, Integer pageSize, Integer pageNum) throws ParseException {
        System.out.println(patent);
        rs = patentService.selectPatentByCondition(patent, application_time_start, application_time_end, public_time_start, public_time_end, authorization_time_start, authorization_time_end, pageSize, pageNum);
        return rs;
    }

    @PostMapping("/patent/updatePatent")
    public Result updatePatent(Patent patent) {
        rs = patentService.updatePatent(patent);
        return rs;
    }

    @PostMapping("/patent/delPatentById")
    public Result delPatentById(Integer patent_id) {
        rs = patentService.delPatentById(patent_id);
        return rs;
    }
}
