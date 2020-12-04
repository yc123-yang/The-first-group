package com.sicnu.controller;

import com.sicnu.service.impl.ProjectTeamExamineServiceImpl;
import com.sicnu.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@CrossOrigin
@Controller
@ResponseBody
public class ProjectTeamExamineController {

    @Resource
    ProjectTeamExamineServiceImpl projectTeamExamineService;

    private Result rs;

    @PostMapping("/teamExamine/selectProjectTeamExamineUser")
    public Result selectProjectTeamExamineUser(Integer pe_id) {
        rs = projectTeamExamineService.selectProjectTeamExamineUser(pe_id);
        return rs;
    }
}
