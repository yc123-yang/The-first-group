package com.sicnu.service;

import com.sicnu.pojo.ProjectExamine;
import com.sicnu.util.Result;

import java.util.HashMap;
import java.util.List;

public interface ProjectExamineService {
    Result addProjectExamine(ProjectExamine projectExamine,Integer[] user_id,String []team_role);
    Result selectProjectExamineByCondition(ProjectExamine projectExamine, String start_time_start, String start_time_end, String complete_time_start, String complete_time_end, String plan_time_start, String plan_time_end, Integer pageNum, Integer pageSize,String apply_time_start,String apply_time_end);
    Result delProjectExamine(Integer pe_id);
}
