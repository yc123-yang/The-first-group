package com.sicnu.controller;

import com.sicnu.pojo.ReviewProject;
import com.sicnu.service.impl.ReviewProjectServiceImpl;
import com.sicnu.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@ResponseBody
public class ReviewProjectController {

    @Resource
    ReviewProjectServiceImpl reviewProjectService;
    /**
     *
     * @param reviewProject
     * @return
     */
    @PostMapping("/review/addReviewProject")
    public Result addReviewProject(ReviewProject reviewProject){
        Result rs = null;
        rs = reviewProjectService.addReviewProject(reviewProject);
        return rs;
    }

    /**
     * 所有待审核的项目
     * @return reviewProjects（待审核的所有项目）
     */
    @PostMapping("/review/selectAllReviewProject")

    public Result selectAllReviewProject() {
        Result rs = null;
        rs =reviewProjectService.selectAllReviewProject();
        return rs ;
    }

    /**
     * 删除待审核的项目
     * @param project_id
     */

    public Result delReviewProject(Integer project_id) {
        Result rs =null;
       rs = reviewProjectService.delReviewProject(project_id);
        return rs;
    }

    /**
     * 根据条件查看要待审核的项目
     * @param reviewProject
     * @return
     */

    public Result selectReviewProject(ReviewProject reviewProject) {
        Result rs = null;
        rs =reviewProjectService.selectReviewProject(reviewProject);

        return rs ;
    }
}
