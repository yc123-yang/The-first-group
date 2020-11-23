package com.sicnu.controller;

import com.sicnu.pojo.Project;
import com.sicnu.pojo.ReviewProject;
import com.sicnu.service.impl.ReviewProjectServiceImpl;
import com.sicnu.util.Result;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@ResponseBody
@CrossOrigin
public class ReviewProjectController  {

    @Resource
    ReviewProjectServiceImpl reviewProjectService;

    private Result rs = null;

    /**
     *项目申请
     * @param reviewProject
     * @return
     */
    @PostMapping("/review/addReviewProject")
    public Result addReviewProject(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") ReviewProject reviewProject){
        rs = reviewProjectService.addReviewProject(reviewProject);
        return rs;
    }

    /**
     * 所有待审核的项目
     * @return reviewProjects（待审核的所有项目）
     */
    @PostMapping("/review/selectAllReviewProject")

    public Result selectAllReviewProject() {
        rs =reviewProjectService.selectAllReviewProject();
        return rs ;
    }

    /**
     * 删除待审核的项目
     * @param project_id
     */
    @PostMapping("/review/delReviewProject")
    public Result delReviewProject(Integer project_id) {
       rs = reviewProjectService.delReviewProject(project_id);
        return rs;
    }


    /**
     *根据条件查询未通过审核得项目
     * @param project
     * @param start_time_start
     * @param start_time_end
     * @param complete_time_start
     * @param plan_time_start
     * @param plan_time_end
     * @param complete_time_end
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */

    @PostMapping("/review/selectReviewProjectByCondition")
    public Result selectReviewProjectByCondition(Project project, String start_time_start, String start_time_end, String complete_time_start, String plan_time_start, String plan_time_end, String complete_time_end, Integer pageNum, Integer pageSize) throws Exception {
        rs =reviewProjectService.selectReviewProjectByCondition(project, start_time_start, start_time_end, complete_time_start, plan_time_start, plan_time_end, complete_time_end, pageNum, pageSize);

        return rs ;
    }
}
