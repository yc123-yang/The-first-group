package com.sicnu.controller;

import com.sicnu.mapper.PeriodicalMapper;
import com.sicnu.pojo.Periodical;
import com.sicnu.service.PeriodicalService;
import com.sicnu.service.impl.PeriodicalServiceImpl;
import com.sicnu.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@Controller
@ResponseBody
public class PeriodicalController {
    @Resource
    PeriodicalServiceImpl periodicalService;
    private Result rs;
    @PostMapping("/periodical/addPeriodical")
    public Result addPeriodical(String pt_name) {
        try {
            rs = periodicalService.addPeriodical(pt_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/periodical/delPeriodical")

    public Result delPeriodical(Integer pt_id) {

        try {
            rs = periodicalService.delPeriodical(pt_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/periodical/findAllPeriodical")

    public Result findAllPeriodical() {
        try {
           rs= periodicalService.findAllPeriodical();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
