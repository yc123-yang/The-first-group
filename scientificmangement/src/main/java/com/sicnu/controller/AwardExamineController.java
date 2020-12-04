package com.sicnu.controller;

import com.sicnu.pojo.AwardExamine;
import com.sicnu.pojo.CacheUser;
import com.sicnu.service.impl.AwardExamineServiceImpl;
import com.sicnu.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
@ResponseBody
public class AwardExamineController {

    @Resource
    AwardExamineServiceImpl awardExamineService;

    private Result rs;

    @GetMapping("/awardExamine/addAwardExamine")
    public Result addAwardExamine(@RequestParam(value = "awardExamine")AwardExamine awardExamine,Integer[] user_id,Double[] contribution) {
        try {
            rs=awardExamineService.addAwardExamine(awardExamine,user_id,contribution);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/awardExamine/selectAwardExamineByCondition")
    public Result selectAwardExamineByCondition(AwardExamine awardExamine, String award_time_start, String award_time_end, Integer pageSize, Integer pageNum, String apply_time_start, String apply_time_end) {
        try {
            rs = awardExamineService.selectAwardExamineByCondition(awardExamine, award_time_start, award_time_end, pageSize, pageNum, apply_time_start, apply_time_end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/awardExamine/delAwardExamineById")
    public Result delAwardExamineById(Integer ae_id) {
        rs=awardExamineService.delAwardExamineById(ae_id);
        return rs;
    }

    @PostMapping("/awardExamine/selectAllAwardExamineByCondition")
    public Result selectAllAwardExamineByCondition(AwardExamine awardExamine, String award_time_start, String award_time_end, Integer pageSize, Integer pageNum, String apply_time_start, String apply_time_end) {
        try {
            rs = awardExamineService.selectAllAwardExamineByCondition(awardExamine, award_time_start, award_time_end, pageSize, pageNum, apply_time_start, apply_time_end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/awardExamine/findAwardExamineById")
    public Result findAwardExamineById(Integer ae_id) {
        try {
            rs = awardExamineService.findAwardExamineById(ae_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
