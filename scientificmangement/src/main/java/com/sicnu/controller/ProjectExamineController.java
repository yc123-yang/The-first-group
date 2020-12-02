package com.sicnu.controller;

import com.sicnu.pojo.ProjectExamine;
import com.sicnu.service.impl.ProjectExamineServiceImpl;
import com.sicnu.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;

@CrossOrigin
@Controller
@ResponseBody
public class ProjectExamineController {
    @Resource
    ProjectExamineServiceImpl projectExamineService;

    private Result rs;

    @PostMapping("/projectExamine/addProjectExamine")
    public Result addProjectExamine(ProjectExamine projectExamine,Integer[] user_id,String[] team_user) {
        try {
            rs = projectExamineService.addProjectExamine(projectExamine, user_id, team_user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/projectExamine/selectProjectExamineByCondition")
    public Result selectProjectExamineByCondition(ProjectExamine projectExamine, String start_time_start, String start_time_end, String complete_time_start, String complete_time_end, String plan_time_start, String plan_time_end, Integer pageNum, Integer pageSize,String apply_time_start,String apply_time_end) {
        try {
            rs =projectExamineService.selectProjectExamineByCondition(projectExamine, start_time_start, start_time_end, complete_time_start, complete_time_end, plan_time_start, plan_time_end, pageNum, pageSize,start_time_start,start_time_end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/projectExamine/delProjectExamine")
    public Result delProjectExamine(Integer pe_id) {
        try {
            rs = projectExamineService.delProjectExamine(pe_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/projectExamine/selectAllProjectExamineByCondition")
    public Result selectAllProjectExamineByCondition(ProjectExamine projectExamine, String start_time_start, String start_time_end, String complete_time_start, String complete_time_end, String plan_time_start, String plan_time_end, Integer pageNum, Integer pageSize,String apply_time_start,String apply_time_end) {
        try {
            rs = projectExamineService.selectAllProjectExamineByCondition(projectExamine, start_time_start, start_time_end, complete_time_start, complete_time_end, plan_time_start, plan_time_end, pageNum, pageSize,start_time_start,start_time_end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
