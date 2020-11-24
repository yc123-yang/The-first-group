package com.sicnu.service.impl;

import com.sicnu.mapper.ReviewProjectMapper;
import com.sicnu.pojo.Project;
import com.sicnu.pojo.ReviewProject;
import com.sicnu.service.ReviewProjectService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 审核
 */
@Service
public class ReviewProjectServiceImpl implements ReviewProjectService {
    @Resource
    ReviewProjectMapper reviewProjectMapper;

    private Result rs = null;

    /**
     * 申报项目
     *
     * @param reviewProject
     * @return
     */
    @Override
    public Result addReviewProject(ReviewProject reviewProject) {
        if (reviewProject.getStart_time().after(reviewProject.getComplete_time())) {
            rs = new Result("0", "完成时间不能小于立项时间", null);
            return rs;
        } else {
            reviewProjectMapper.addReviewProject(reviewProject);
            rs = new Result("0", "您的项目已经上传审核，请您耐心等待审核结果", null);
            return rs;
        }
    }

    /**
     * 所有待审核的项目
     *
     * @return reviewProjects（待审核的所有项目）
     */
    @Override
    public Result selectAllReviewProject() {
        List<ReviewProject> reviewProjects = reviewProjectMapper.selectAllReviewProject();
        return rs = new Result("0", null, reviewProjects);
    }

    /**
     * 删除待审核的项目
     *
     * @param project_id
     */

    @Override
    public Result delReviewProject(Integer project_id) {
        reviewProjectMapper.delReviewProject(project_id);
        rs = new Result("0", "删除成功", null);
        return rs;
    }

    /**
     * 根据条件查询待审核项目
     *
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
    @Override
    public Result selectReviewProjectByCondition(Project project, String start_time_start, String start_time_end, String complete_time_start, String plan_time_start, String plan_time_end, String complete_time_end, Integer pageNum, Integer pageSize) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        //时间格式设置

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(project.getProject_name());
        map.put("project_name", project.getProject_name());
        map.put("department_id", project.getDepartment_id());
        map.put("project_id", project.getProject_id());
        map.put("sc_id", project.getSc_id());
        map.put("subject_id", project.getSubject_id());
        map.put("nature_id", project.getNature_id());
        map.put("level_id", project.getLevel_id());
        map.put("status_id", project.getStatus_id());
        map.put("sd_id", project.getSd_id());
        map.put("outlay", project.getOutlay());
        map.put("approval_number", project.getApproval_number());
        map.put("ct_id", project.getCt_id());
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        if (!start_time_start.equals("")) {
            map.put("start_time_start", sdf.parse(start_time_start));
        }
        if (!start_time_end.equals("")) {
            map.put("start_time_end", sdf.parse(start_time_end));
        }
        if (!complete_time_start.equals("")) {
            map.put("complete_time_start", sdf.parse(complete_time_start));
        }
        if (!complete_time_end.equals("")) {
            map.put("complete_time_end", sdf.parse(complete_time_end));
        }
        if (!plan_time_start.equals("")) {
            map.put("plan_time_start", sdf.parse(plan_time_start));
        }
        if (!plan_time_end.equals("")) {
            map.put("plan_time_end", sdf.parse(plan_time_end));
        }
        System.out.println(map);
        List<ReviewProject> reviewProjects = reviewProjectMapper.selectReviewProjectByCondition(map);
        Integer total = reviewProjectMapper.selectTotalReviewProject(map);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("total", total);
        List<Object> list = new ArrayList<>();
        list.add(map1);
        list.add(reviewProjects);
        return rs = new Result("200", null, list);

    }


}
