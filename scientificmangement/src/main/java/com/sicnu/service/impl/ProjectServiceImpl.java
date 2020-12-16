package com.sicnu.service.impl;

import com.alibaba.fastjson.JSON;
import com.sicnu.mapper.*;
import com.sicnu.pojo.CacheUser;
import com.sicnu.pojo.Patent;
import com.sicnu.pojo.Project;
import com.sicnu.pojo.User;
import com.sicnu.pojo.teamExamine.PatentTeamExamine;
import com.sicnu.pojo.teamExamine.ProjectTeamExamine;
import com.sicnu.pojo.teamMap.PatentTeamMap;
import com.sicnu.pojo.teamMap.ProjectTeamMap;
import com.sicnu.pojo.teamMap.UserAuth;
import com.sicnu.service.ProjectService;
import com.sicnu.util.Result;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目
 */
@Service("ProjectServiceImpl")
public class ProjectServiceImpl implements ProjectService {

    @Resource
    ProjectMapper projectDao;

    @Resource
    UserMapper userDao;

    @Resource
    JavaMailSenderImpl mailSender;

    @Resource
    ProjectTeamMapper projectTeamMapper;
    @Resource
    ProjectTeamExamineMapper projectTeamExamineMapper;

    @Resource
    CacheUserMapper cacheUserMapper;

    @Resource
    ProjectExamineMapper projectExamineMapper;

    @Resource
    RoleAuthMapper roleAuthMapper;
    private Result rs = null;
    @Resource
    private HttpSession session;
    /**
     * 审核项目
     *
     * @param project      前端传来项目信息
     * @param checkMessage 审核结果反馈 fail不通过 success 通过
     * @param message      审核不通过反馈的原因
     * @return
     * @throws MessagingException
     */
    @Override
    public Result addProject(Project project, String checkMessage, String message) throws MessagingException {

        System.out.println("调用");
        try {

            Integer projectExamineId = projectExamineMapper.selectProjectExamineId(project.getLeader_id(), project.getProject_name());
            List<ProjectTeamExamine> projectTeamExamines = projectTeamExamineMapper.selectProjectTeamExamineById(projectExamineId);
            //获取项目负责人信息
            User user = userDao.findUserById(project.getLeader_id());
            //创建邮件环境，反馈信息
            MimeMessage mailMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage);

            //如果不通过审核反馈
            if (checkMessage.equals("fail")) {
                helper.setSubject("项目上传科研项目管理系统审核");
                helper.setText("<p>您的项目申报审核未通过，因为<span style='color:blue;text-decoration:underline'>" + message + "</span>,请您解决之后重新申请。</p>", true);
                //负责人邮箱
                helper.setTo(user.getUser_email());
                helper.setFrom("1776557392@qq.com");
                mailSender.send(mailMessage);
                //从待审核里面删除
                projectTeamExamineMapper.delProjectTeamExamineTeam(projectExamineId);
                projectExamineMapper.delProjectExamine(project.getLeader_id(),project.getProject_name());
                rs = new Result("400", "审核结果已反馈", null);
            } else {
                projectDao.addProject(project);
                //获取项目id 返给用户
                Integer projectId = projectDao.selectProjectId(project.getLeader_id(), project.getProject_name());
                helper.setSubject("项目上传科研项目管理系统审核");
                helper.setText("<p>您的项目申报审核成功，项目编号为：<span style='color:blue;text-decoration:underline'>" + projectId + "</span>,请勿遗忘。</p>", true);
                helper.setTo(user.getUser_email());
                helper.setFrom("1776557392@qq.com");
                mailSender.send(mailMessage);
                for (ProjectTeamExamine projectTeamExamine : projectTeamExamines) {
                    projectTeamMapper.addProjectTeamUser(projectId,projectTeamExamine.getUser_id(),projectTeamExamine.getTeam_role());
                }
                //从待审核删除
                projectTeamExamineMapper.delProjectTeamExamineTeam(projectExamineId);
                projectExamineMapper.delProjectExamine(project.getLeader_id(),project.getProject_name());
                rs = new Result("200", "审核结果已反馈", null);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (MailException e) {
            e.printStackTrace();
        }

        return rs;
    }

    /**
     * 根据条件查询项目信息
     *
     * @param project
     * @param start_time_start
     * @param start_time_end
     * @param complete_time_start
     * @param complete_time_end
     * @param plan_time_start
     * @param plan_time_end
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public Result selectProjectByCondition(Project project, String start_time_start, String start_time_end, String complete_time_start, String complete_time_end, String plan_time_start, String plan_time_end, Integer pageNum, Integer pageSize) throws Exception {
        List<Object> list = null;
        try {
            Map<String, Object> map = new HashMap<String, Object>();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            User user = (User) session.getAttribute("user");
            //获取登陆用户的缓存信息
            List<CacheUser> cacheUsers = cacheUserMapper.findAllCacheUser();
            //获取登录用户的id
            Integer uid = user.getUser_id();
            System.out.println(uid);
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

//            User user = userDao.findUserById(uid);
            List<UserAuth> userAuths = roleAuthMapper.findUserAuth(user.getRole_id());
            int cnt =0;
            for (UserAuth userAuth : userAuths) {
                System.out.println(userAuth.getAuth_resource());
                if (userAuth.getAuth_resource().equals("/projects/allProjects")){
                    cnt=1;
                }
            }
            if (cnt==1){
                List<Project> projects = projectDao.selectProjectByCondition(map);
                //根据条件获取的项目条数
                Integer total = projectDao.selectTotalProject(map);
                List<Map> mapList = new ArrayList<>();
                for (int i = 0; i < projects.size(); i++) {
                    Map temp = JSON.parseObject(JSON.toJSONString(projects.get(i)),Map.class);
                    temp.put("user_name",userDao.findUserById(projects.get(i).getLeader_id()).getUser_name());
                    temp.put("complete_time()",sdf.format(projects.get(i).getComplete_time()));
                    temp.put("plan_time",sdf.format(projects.get(i).getPlan_time()));
                    temp.put("start_time",sdf.format(projects.get(i).getStart_time()));

                    mapList.add(temp);
                }
                Map<String, Object> map1 = new HashMap<>();
                map1.put("total", total);
                list = new ArrayList<>();
                list.add(map1);
                list.add(mapList);
            }else{
                map.put("leader_id", uid);
                //根据传来的条件筛选用户
                List<Project> projects = projectDao.selectProjectByCondition(map);
                //根据条件获取的项目条数
                Integer total = projectDao.selectTotalProject(map);
                List<Map> mapList = new ArrayList<>();
                for (int i = 0; i < projects.size(); i++) {
                    Map temp = JSON.parseObject(JSON.toJSONString(projects.get(i)),Map.class);
                    temp.put("user_name",userDao.findUserById(projects.get(i).getLeader_id()).getUser_name());
                    temp.put("complete_time()",sdf.format(projects.get(i).getComplete_time()));
                    temp.put("plan_time",sdf.format(projects.get(i).getPlan_time()));
                    temp.put("start_time",sdf.format(projects.get(i).getStart_time()));

                    mapList.add(temp);
                }
                Map<String, Object> map1 = new HashMap<>();
                map1.put("total", total);
                list = new ArrayList<>();
                list.add(map1);
                list.add(mapList);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return rs = new Result("200", null, list);
    }


    /**
     * 编辑项目信息
     *
     * @param project 前端传到后台的修改信息
     * @return
     */

    @Override
    public Result updateProject(Project project) {

        try {
            projectDao.updateProject(project);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs = new Result("200", "修改成功", null);

    }

    /**
     * 项目组成员信息
     *
     * @param project_Id 项目id
     * @return
     */
    @Override
    public Result selectTeamByPid(Integer project_Id) {
        List<Object> list = null;
        try {
            List<Project> projects = projectDao.selectTeamByPid(project_Id);
            list = new ArrayList<>();
            //获取组员信息
            for (Project project1 : projects) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("name", project1.getProjectTeams().get(0).getUsers().get(0).getUser_name());
                map.put("role_id", project1.getProjectTeams().get(0).getUsers().get(0).getRole_id());
                map.put("depart_id", project1.getProjectTeams().get(0).getUsers().get(0).getDepartment_id());
                list.add(map);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs = new Result("200", null, list);
    }


    /**
     * 删除某个项目
     *
     * @param project_id
     * @return
     */

    @Override
    public Result delProject(Integer project_id) {
        try {
            projectDao.delProject(project_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs = new Result("200", "删除成功", null);
    }

    /**
     * 根据前端传来的条件查询 项目信息
     *
     * @param project 前端传到后台的查询信息
     * @return
     */
    @Override
    public Result selectAllProjectByCondition(Project project, String start_time_start, String start_time_end, String complete_time_start, String complete_time_end, String plan_time_start, String plan_time_end, Integer pageNum, Integer pageSize) throws Exception {
        List<Object> list = null;
        try {
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

            List<Project> projects = projectDao.selectProjectByCondition(map);
            Integer total = projectDao.selectTotalProject(map);
            List<Map> mapList = new ArrayList<>();
            for (int i = 0; i < projects.size(); i++) {
                Map temp = JSON.parseObject(JSON.toJSONString(projects.get(i)),Map.class);
                temp.put("user_name",userDao.findUserById(projects.get(i).getLeader_id()).getUser_name());
                temp.put("complete_time()",sdf.format(projects.get(i).getComplete_time()));
                temp.put("plan_time",sdf.format(projects.get(i).getPlan_time()));
                temp.put("start_time",sdf.format(projects.get(i).getStart_time()));

                mapList.add(temp);
            }
            Map<String, Object> map1 = new HashMap<>();
            map1.put("total", total);
            list = new ArrayList<>();
            list.add(map1);
            list.add(mapList);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs = new Result("200", null, list);
    }

    /**
     * 根据项目id获取项目信息
     *
     * @param project_id
     * @return
     */
    @Override
    public Result findProjectById(Integer project_id) {
        try {
            User user = (User) session.getAttribute("user");
            Project project = projectDao.findProjectById(project_id);
            Map map = JSON.parseObject(JSON.toJSONString(project), Map.class);
            map.put("user_name", user.getUser_name());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化对象
            map.put("complete_time", sdf.format(project.getComplete_time()));
            map.put("start_time", sdf.format(project.getStart_time()));
            map.put("plan_time", sdf.format(project.getPlan_time()));
            rs = new Result("200", null, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public  Result findPersonalProjectMessage(Integer project_id){
        try {
            Project project = projectDao.findProjectById(project_id);
            List<ProjectTeamMap> projectTeamMaps = projectTeamMapper.selectProjectTeam(project_id);
            Map<String,Object> map =new HashMap<>();
            map.put("project",project);
            map.put("projectTeamMaps",projectTeamMaps);
            rs = new Result("200",null,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
