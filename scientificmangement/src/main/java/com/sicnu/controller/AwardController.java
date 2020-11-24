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
    public Result addAward(Award award) {
        rs = awardService.addAward(award);
        return rs;
    }

    @PostMapping("/award/selectAwardByCondition")
    public Result selectAwardByCondition(Award award, String award_time_start, String award_time_end, Integer pageSize, Integer pageNum) throws ParseException {
        rs = awardService.selectAwardByCondition(award, award_time_start, award_time_end, pageSize, pageNum);
        return rs;
    }

    @PostMapping("/award/updateAward")
    public Result updateAward(Award award) {
        rs = awardService.updateAward(award);
        return rs;
    }

    @PostMapping("/award/delAward")
    public Result delAward(Integer award_id) {
        rs = awardService.delAward(award_id);
        return rs;
    }
}
