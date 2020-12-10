package com.sicnu.controller;

import com.sicnu.pojo.Periodical;
import com.sicnu.service.impl.PeriodicalServiceImpl;
import com.sicnu.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@CrossOrigin
@Controller
@ResponseBody
public class PeriodicalController {
    @Resource
    PeriodicalServiceImpl periodicalService;

    private Result rs;

    @PostMapping("/periodical/addPeriodical")
    @RequiresPermissions("/data")
    public Result addPeriodical(String periodical_name,Integer pp_score) {
        try {
            rs = periodicalService.addPeriodical(periodical_name,pp_score);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/periodical/delPeriodical")
    @RequiresPermissions("/data")
    public Result delPeriodical(Integer periodical_id) {

        try {
            rs = periodicalService.delPeriodical(periodical_id);
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
    @PostMapping("/periodical/updatePeriodical")
    @RequiresPermissions("/data")
    public Result updatePeriodical(Periodical periodical) {
        try {
           rs= periodicalService.updatePeriodical(periodical);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
