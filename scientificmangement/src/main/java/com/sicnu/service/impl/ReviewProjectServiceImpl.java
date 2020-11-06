package com.sicnu.service.impl;

import com.sicnu.mapper.ReviewProjectMapper;
import com.sicnu.pojo.ReviewProject;
import com.sicnu.service.ReviewProjectService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReviewProjectServiceImpl implements ReviewProjectService {
    @Resource
    ReviewProjectMapper reviewProjectMapper;

    /**
     * 申报项目
     * @param reviewProject
     * @return
     */
    @Override
    public Result addReviewProject(ReviewProject reviewProject) {
        Result rs = null;
        if (reviewProject.getStart_time().after(reviewProject.getComplete_time())){
            rs =  new Result("0","完成时间不能小于立项时间",null);
            return rs;
        }else{
            reviewProjectMapper.addReviewProject(reviewProject);
            rs =  new Result("0","您的项目已经上传审核，请您耐心等待审核结果",null);
            return rs;
        }
    }

    /**
     * 所有待审核的项目
     * @return reviewProjects（待审核的所有项目）
     */
    @Override
    public Result selectAllReviewProject() {
        Result rs = null;
        List<ReviewProject> reviewProjects =reviewProjectMapper.selectAllReviewProject();
        return rs = new Result("0",null,reviewProjects);
    }

    /**
     * 删除待审核的项目
     * @param project_id
     */

    @Override
    public Result delReviewProject(Integer project_id) {
        Result rs =null;
        reviewProjectMapper.delReviewProject(project_id);
        rs = new Result("0","删除成功",null);
        return rs;
    }

    /**
     * 根据条件查看要待审核的项目
     * @param reviewProject
     * @return
     */
    @Override
    public Result selectReviewProject(ReviewProject reviewProject) {
        Result rs = null;
        List<ReviewProject> reviewProjects =reviewProjectMapper.selectReviewProject(reviewProject);

        return rs = new Result("0",null,reviewProjects);
    }
}
