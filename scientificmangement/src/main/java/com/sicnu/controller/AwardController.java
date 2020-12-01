package com.sicnu.controller;

import com.sicnu.pojo.Award;
import com.sicnu.service.impl.AwardServiceImpl;
import com.sicnu.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;

@CrossOrigin
@Controller
@ResponseBody
public class AwardController {
    @Resource
    AwardServiceImpl awardService;

    private Result rs;

    @PostMapping("/award/addAward")

    public Result addAward(Award award,String checkMessage,String message) {
        try {
            rs = awardService.addAward(award, checkMessage, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/award/selectAwardByCondition")
    public Result selectAwardByCondition(Award award, String award_time_start, String award_time_end, Integer pageSize, Integer pageNum) throws ParseException {
        try {
            rs = awardService.selectAwardByCondition(award, award_time_start, award_time_end, pageSize, pageNum);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/award/updateAward")
    public Result updateAward(Award award) {
        try {
            rs = awardService.updateAward(award);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/award/delAward")
    public Result delAward(Integer award_id) {
        try {
            rs = awardService.delAward(award_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/award/selectAllAwardByCondition")
    public Result selectAllAwardByCondition(Award award, String award_time_start, String award_time_end, Integer pageSize, Integer pageNum) throws ParseException {
        try {
            rs = awardService.selectAllAwardByCondition(award, award_time_start, award_time_end, pageSize, pageNum);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
