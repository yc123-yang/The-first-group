package com.sicnu.controller;

import com.sicnu.mapper.PatentTeamExamineMapper;
import com.sicnu.pojo.teamMap.PatentTeamMap;
import com.sicnu.service.impl.PatentTeamExamineServiceImpl;
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
public class PatentTeamExamineController {

    @Resource
    PatentTeamExamineServiceImpl patentTeamExamineService;
    private Result rs;
    @PostMapping("/teamExamine/selectPatentTeamExamineUser")
    public Result selectPatentTeamExamineUser(Integer pe_id) {
        try {
            rs = patentTeamExamineService.selectPatentTeamExamineUser(pe_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
