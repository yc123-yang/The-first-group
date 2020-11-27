package com.sicnu.controller;

import com.sicnu.mapper.AwardRankMapper;
import com.sicnu.pojo.AwardRank;
import com.sicnu.service.impl.AwardRankServiceImpl;
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
public class AwardRankController {
    @Resource
    AwardRankServiceImpl awardRankService;

    private Result rs;

    @PostMapping("/awardRank/addAwardRank")
    public Result addAwardRank(String ar_name) {
        try {
            rs = awardRankService.addAwardRank(ar_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/awardRank/delAwardRank")

    public Result delAwardRank(Integer ar_id) {
        try {
            rs =awardRankService.delAwardRank(ar_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/awardRank/findAllAwardRank")

    public Result findAllAwardRank() {
        try {
            rs = awardRankService.findAllAwardRank();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/awardRank/updateAwardRank")
    public Result updateAwardRank(AwardRank awardRank) {

        try {
            rs =awardRankService.updateAwardRank(awardRank);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
