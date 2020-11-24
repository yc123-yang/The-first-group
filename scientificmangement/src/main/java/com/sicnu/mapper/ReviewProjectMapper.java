package com.sicnu.mapper;

import com.sicnu.pojo.Project;
import com.sicnu.pojo.ReviewProject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ReviewProjectMapper {
    void addReviewProject(ReviewProject reviewProject);

    List<ReviewProject> selectAllReviewProject();

    void delReviewProject(Integer project_id);

    List<ReviewProject> selectReviewProjectByCondition(Map<String, Object> map);

    Integer selectTotalReviewProject(Map<String, Object> map);
}
