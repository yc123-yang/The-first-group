package com.sicnu.controller;

import com.sicnu.pojo.PatentExamine;
import com.sicnu.service.impl.PatentExamineServiceImpl;
import com.sicnu.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@CrossOrigin
@ResponseBody
public class PatentExamineController {

    @Resource
    PatentExamineServiceImpl patentExamineService;

    private Result rs;


    @PostMapping("/patentExamine/addPatentExamine")
    public Result addPatentExamine(PatentExamine patentExamine,Integer[] user_id,Double[] contribution) {
        try {
            rs=patentExamineService.addPatentExamine(patentExamine, user_id, contribution);
          } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/patentExamine/selectPatentExamineByCondition")
    public Result selectPatentExamineByCondition(PatentExamine patentExamine, String application_time_start, String application_time_end, String public_time_start, String public_time_end, String authorization_time_start, String authorization_time_end, Integer pageSize, Integer pageNum, String apply_time_start, String apply_time_end) {
        List<Object> list = null;
        try {

            rs = patentExamineService.selectPatentExamineByCondition(patentExamine, application_time_start, application_time_end, public_time_start, public_time_end, authorization_time_start, authorization_time_end, pageSize, pageNum, apply_time_start, apply_time_end);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/patentExamine/delPatentExamineById")
    public Result delPatentExamineById(Integer pe_id) {
        try {
            rs=patentExamineService.delPatentExamineById(pe_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/patentExamine/selectAllPatentExamineByCondition")
    public Result selectAllPatentExamineByCondition(PatentExamine patentExamine, String application_time_start, String application_time_end, String public_time_start, String public_time_end, String authorization_time_start, String authorization_time_end, Integer pageSize, Integer pageNum, String apply_time_start, String apply_time_end) {
        try {

            rs = patentExamineService.selectAllPatentExamineByCondition(patentExamine, application_time_start, application_time_end, public_time_start, public_time_end, authorization_time_start, authorization_time_end, pageSize, pageNum, apply_time_start, apply_time_end);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
