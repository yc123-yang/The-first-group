package com.sicnu.service;

import com.sicnu.pojo.ReviewProject;
import com.sicnu.util.Result;

import java.util.List;

public interface ReviewProjectService {
    Result addReviewProject(ReviewProject reviewProject);
    Result selectAllReviewProject();
    Result delReviewProject(Integer project_id);
    Result selectReviewProject(ReviewProject reviewProject);
}
