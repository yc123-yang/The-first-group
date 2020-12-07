package com.sicnu.service.impl;

import com.sicnu.mapper.CacheUserMapper;
import com.sicnu.mapper.ProjectExamineMapper;
import com.sicnu.mapper.ProjectTeamExamineMapper;
import com.sicnu.mapper.UserMapper;
import com.sicnu.pojo.CacheUser;
import com.sicnu.pojo.Project;
import com.sicnu.pojo.ProjectExamine;
import com.sicnu.service.ProjectExamineService;
import com.sicnu.util.Result;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProjectExamineServiceImpl implements ProjectExamineService {
    @Resource
    ProjectExamineMapper projectExamineMapper;

    private Result rs;
    @Resource
    ProjectTeamExamineMapper projectTeamExamineMapper;
    @Resource
    CacheUserMapper cacheUserMapper;

    @Override
    public Result addProjectExamine(ProjectExamine projectExamine,Integer[] user_id,String []team_role) {
        try {
            projectExamine.setExamine_status("未审核");
            projectExamine.setApply_time(new Date());
            projectExamine.setReviewer_id(1);
            projectExamineMapper.addProjectExamine(projectExamine);
            Integer project_id = projectExamineMapper.selectProjectExamineId(projectExamine.getLeader_id(),projectExamine.getProject_name());
            for (int i = 0; i < user_id.length; i++) {
                projectTeamExamineMapper.addProjectTeamExamineUser(project_id,user_id[i],team_role[i]);
            }
            rs = new Result("200","您的项目已经上传审核，请您耐心等待",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result selectProjectExamineByCondition(ProjectExamine projectExamine, String start_time_start, String start_time_end, String complete_time_start, String complete_time_end, String plan_time_start, String plan_time_end, Integer pageNum, Integer pageSize,String apply_time_start,String apply_time_end) {
        List<Object> list = null;
        try {
            Map<String, Object> map = new HashMap<String, Object>();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            //获取登陆用户的缓存信息
            List<CacheUser> cacheUsers = cacheUserMapper.findAllCacheUser();
            //获取登录用户的id
            Integer uid = cacheUsers.get(0).getUser_id();
            System.out.println(uid);
            System.out.println(projectExamine.getProject_name());
            map.put("project_name", projectExamine.getProject_name());
            map.put("leader_id", uid);
            map.put("department_id", projectExamine.getDepartment_id());
            map.put("sc_id", projectExamine.getSc_id());
            map.put("subject_id", projectExamine.getSubject_id());
            map.put("nature_id", projectExamine.getNature_id());
            map.put("level_id", projectExamine.getLevel_id());
            map.put("status_id", projectExamine.getStatus_id());
            map.put("sd_id", projectExamine.getSd_id());
            map.put("outlay", projectExamine.getOutlay());
            map.put("approval_number", projectExamine.getApproval_number());
            map.put("ct_id", projectExamine.getCt_id());
            map.put("examine_status",projectExamine.getExamine_status());
            map.put("reviewer_id",projectExamine.getReviewer_id());
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
            if (!apply_time_start.equals("")) {
                map.put("apply_time_start", sdf.parse(apply_time_start));
            }
            if (!apply_time_end.equals("")) {
                map.put("apply_time_end", sdf.parse(apply_time_end));
            }
            System.out.println(map);
            //根据传来的条件筛选用户
            List<ProjectExamine> ProjectExamines = projectExamineMapper.selectProjectExamineByCondition(map);
            //根据条件获取的项目条数
            Integer total = projectExamineMapper.selectTotalProjectExamine(map);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("total", total);
            list = new ArrayList<>();
            list.add(map1);
            list.add(ProjectExamines);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return rs = new Result("200", null, list);
    }

    @Override
    public Result delProjectExamine(Integer pe_id) {
        try {
            projectExamineMapper.delProjectExamineById(pe_id);
            rs = new Result("200", "删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findProjectExamineById(Integer pe_id) {
        try {
            ProjectExamine projectExamine = projectExamineMapper.findProjectExamineById(pe_id);
            rs = new Result("200",null,projectExamine);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public Result selectAllProjectExamineByCondition(ProjectExamine projectExamine, String start_time_start, String start_time_end, String complete_time_start, String complete_time_end, String plan_time_start, String plan_time_end, Integer pageNum, Integer pageSize,String apply_time_start,String apply_time_end) {
        List<Object> list = null;
        try {
            Map<String, Object> map = new HashMap<String, Object>();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            System.out.println(projectExamine.getProject_name());
            map.put("project_name", projectExamine.getProject_name());
            map.put("department_id", projectExamine.getDepartment_id());
            map.put("sc_id", projectExamine.getSc_id());
            map.put("subject_id", projectExamine.getSubject_id());
            map.put("nature_id", projectExamine.getNature_id());
            map.put("level_id", projectExamine.getLevel_id());
            map.put("status_id", projectExamine.getStatus_id());
            map.put("sd_id", projectExamine.getSd_id());
            map.put("outlay", projectExamine.getOutlay());
            map.put("approval_number", projectExamine.getApproval_number());
            map.put("ct_id", projectExamine.getCt_id());
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
            //根据传来的条件筛选用户
            List<ProjectExamine> ProjectExamines = projectExamineMapper.selectProjectExamineByCondition(map);
            //根据条件获取的项目条数
            Integer total = projectExamineMapper.selectTotalProjectExamine(map);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("total", total);
            list = new ArrayList<>();
            list.add(map1);
            list.add(ProjectExamines);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return rs = new Result("200", null, list);
    }
}
