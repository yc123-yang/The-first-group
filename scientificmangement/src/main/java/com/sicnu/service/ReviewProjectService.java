package com.sicnu.service;

import com.sicnu.pojo.Project;
import com.sicnu.pojo.ReviewProject;
import com.sicnu.util.Result;

import java.util.List;

public interface ReviewProjectService {
    Result addReviewProject(ReviewProject reviewProject);

    Result selectAllReviewProject();

    Result delReviewProject(Integer project_id);

    Result selectReviewProjectByCondition(Project project, String start_time_start, String start_time_end, String complete_time_start, String plan_time_start, String plan_time_end, String complete_time_end, Integer pageNum, Integer pageSize) throws Exception;
}
