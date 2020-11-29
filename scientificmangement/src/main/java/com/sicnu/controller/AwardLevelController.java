package com.sicnu.controller;

import com.sicnu.mapper.AwardLevelMapper;
import com.sicnu.pojo.AwardLevel;
import com.sicnu.service.impl.AwardLevelServiceImpl;
import com.sicnu.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@Controller
@ResponseBody
/**
 * 获奖等级
 */
public class AwardLevelController {
    @Resource
    AwardLevelServiceImpl awardLevelService;

    private Result rs;
    @PostMapping("/awardLevel/addAwardLevel")
    @RequiresPermissions("/data")

    public Result addAwardLevel(String al_name) {
        try {
            rs   = awardLevelService.addAwardLevel(al_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/awardLevel/delAwardLevel")
    @RequiresPermissions("/data")
    public Result delAwardLevel(Integer al_id) {
        try {
            rs=awardLevelService.delAwardLevel(al_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/awardLevel/findAllAwardLevel")
    public Result findAllAwardLevel() {
        try {
            rs = awardLevelService.findAllAwardLevel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/awardLevel/updateAwardLevel")
    @RequiresPermissions("/data")
    public Result updateAwardLevel(AwardLevel awardLevel) {
        try {
                rs = awardLevelService.updateAwardLevel(awardLevel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
