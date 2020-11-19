package com.sicnu.service.impl;

import com.sicnu.mapper.ProjectTeamMapper;
import com.sicnu.pojo.CacheUser;
import com.sicnu.mapper.CacheUserMapper;
import com.sicnu.mapper.ProjectMapper;
import com.sicnu.mapper.UserMapper;
import com.sicnu.pojo.Project;
import com.sicnu.pojo.User;
import com.sicnu.service.ProjectService;
import com.sicnu.util.Result;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("ProjectServiceImpl")
public class ProjectServiceImpl implements ProjectService {

    @Resource
    ReviewProjectServiceImpl reviewProjectService;
    @Resource
    ProjectMapper projectDao;

    @Resource
    UserMapper userDao;

    @Resource
    JavaMailSenderImpl mailSender;

    @Resource
    CacheUserMapper cacheUserMapper;

    @Resource
    ProjectTeamMapper teamMapper;

    private Result rs = null;

    /**
     *
     * 审核项目
     * @param project 前端传来项目信息
     * @param checkMessage 审核结果反馈 fail不通过 success 通过
     * @param message 审核不通过反馈的原因
     * @return
     * @throws MessagingException
     */
    @Override
    public Result addProject(Project project,String checkMessage,String message) throws MessagingException {

        System.out.println("调用");
        //获取项目id 返给用户
        Integer projectId = projectDao.selectProjectId(project.getLeader_id(),project.getProject_name());
        //获取项目负责人信息
        User user =  userDao.findUserById(project.getLeader_id());
        //创建邮件环境，反馈信息
        MimeMessage mailMessage =  mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage);

        //如果不通过审核反馈
        if (checkMessage.equals("fail")){
            helper.setSubject("高校科研管理系统注册验证码");
            helper.setText("<p>您的项目申报审核未通过，因为<span style='color:blue;text-decoration:underline'>"+message+"</span>,请您解决之后重新申请。</p>",true);
           //负责人邮箱
            helper.setTo(user.getUser_email());
            helper.setFrom("1776557392@qq.com");
            mailSender.send(mailMessage);
            //从待审核里面删除
            reviewProjectService.delReviewProject(projectId);
            rs = new Result("400","审核结果已反馈",null);
        }else{
            project.setStatus_id(2);
            projectDao.addProject(project);
            helper.setSubject("高校科研管理系统注册验证码");
            helper.setText("<p>您的项目申报审核成功，项目编号为：<span style='color:blue;text-decoration:underline'>"+projectId+"</span>,请勿遗忘。</p>",true);
            helper.setTo(user.getUser_email());
            helper.setFrom("1776557392@qq.com");
            mailSender.send(mailMessage);
            //从待审核删除
            reviewProjectService.delReviewProject(projectId);
            rs = new Result("200","审核结果已反馈",null);
        }

        return rs;
    }

    /**
     * 根据条件查询项目信息
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
    public Result selectProjectByCondition(Project project, String start_time_start, String start_time_end, String complete_time_start, String complete_time_end, String plan_time_start,String plan_time_end,Integer pageNum,Integer pageSize) throws Exception {
        Map<String,Object> map = new HashMap<String,Object>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //获取登陆用户的缓存信息
        List<CacheUser> cacheUsers = cacheUserMapper.findAllCacheUser();
        //获取登录用户的id
        Integer uid = cacheUsers.get(0).getUser_id();
        System.out.println(uid);
        System.out.println(project.getProject_name());
        map.put("project_name",project.getProject_name());
        map.put("leader_id",uid);
        map.put("department_id",project.getDepartment_id());
        map.put("project_id",project.getProject_id());
        map.put("sc_id",project.getSc_id());
        map.put("subject_id",project.getSubject_id());
        map.put("nature_id",project.getNature_id());
        map.put("level_id",project.getLevel_id());
        map.put("status_id",project.getStatus_id());
        map.put("sd_id",project.getSd_id());
        map.put("outlay",project.getOutlay());
        map.put("approval_number",project.getApproval_number());
        map.put("ct_id",project.getCt_id());
        map.put("pageNum",pageNum);
        map.put("pageSize",pageSize);
        if ( !start_time_start.equals("")){
            map.put("start_time_start", sdf.parse(start_time_start));
        }
        if ( !start_time_end.equals("")){
            map.put("start_time_end",sdf.parse(start_time_end));
        }
        if ( !complete_time_start.equals("")){
            map.put("complete_time_start",sdf.parse(complete_time_start));
        }
        if ( !complete_time_end.equals("")){
            map.put("complete_time_end",sdf.parse(complete_time_end));
        }
        if ( !plan_time_start.equals("")){
            map.put("plan_time_start",sdf.parse(plan_time_start));
        }
        if ( !plan_time_end.equals("")){
            map.put("plan_time_end",sdf.parse(plan_time_end));
        }
        System.out.println(map);
        //根据传来的条件筛选用户
        List<Project> projects = projectDao.selectProjectByCondition(map);
        //根据条件获取的项目条数
        Integer total = projectDao.selectTotalProject(map);
        Map<String,Object> map1 = new HashMap<>();
        map1.put("total",total);
        List<Object> list = new ArrayList<>();
        list.add(map1);
        list.add(projects);

        return rs = new Result("200",null,list);
    }


    /**
     * 编辑项目信息
     * @param project 前端传到后台的修改信息
     * @return
     */

    @Override
    public Result updateProject(Project project) {

        projectDao.updateProject(project);
        return rs = new Result("200","修改成功",null);

    }

    /**
     * 项目组成员信息
     * @param project_Id 项目id
     * @return
     */
    @Override
    public Result selectTeamByPid(Integer project_Id) {
        List<Project> projects = projectDao.selectTeamByPid(project_Id);
        List<Object> list = new ArrayList<>();
        //获取组员信息
        for (Project project1 : projects) {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("name",project1.getProjectTeams().get(0).getUsers().get(0).getUser_name());
            map.put("role_id",project1.getProjectTeams().get(0).getUsers().get(0).getRole_id());
            map.put("user_status",project1.getProjectTeams().get(0).getUser_role());
            map.put("depart_id",project1.getProjectTeams().get(0).getUsers().get(0).getDepartment_id());
            list.add(map);

        }
        return rs = new Result("200",null,list);
    }


    /**
     * 删除某个项目
     * @param project_id
     * @return
     */

    @Override
    public Result delProject(Integer project_id) {
        projectDao.delProject(project_id);
        return rs = new Result("200","删除成功",null);
    }

    /**
     * 根据前端传来的条件查询 项目信息
     * @param project 前端传到后台的查询信息
     * @return
     */
    @Override
    public Result selectAllProjectByCondition(Project project, String start_time_start, String start_time_end, String complete_time_start, String complete_time_end, String plan_time_start,String plan_time_end,Integer pageNum,Integer pageSize) throws Exception {
        Map<String,Object> map = new HashMap<String,Object>();

        //时间格式设置

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(project.getProject_name());
        map.put("project_name",project.getProject_name());
        map.put("department_id",project.getDepartment_id());
        map.put("project_id",project.getProject_id());
        map.put("sc_id",project.getSc_id());
        map.put("subject_id",project.getSubject_id());
        map.put("nature_id",project.getNature_id());
        map.put("level_id",project.getLevel_id());
        map.put("status_id",project.getStatus_id());
        map.put("sd_id",project.getSd_id());
        map.put("outlay",project.getOutlay());
        map.put("approval_number",project.getApproval_number());
        map.put("ct_id",project.getCt_id());
        map.put("pageNum",pageNum);
        map.put("pageSize",pageSize);
        if ( !start_time_start.equals("")){
            map.put("start_time_start", sdf.parse(start_time_start));
        }
        if ( !start_time_end.equals("")){
            map.put("start_time_end",sdf.parse(start_time_end));
        }
        if ( !complete_time_start.equals("")){
            map.put("complete_time_start",sdf.parse(complete_time_start));
        }
        if ( !complete_time_end.equals("")){
            map.put("complete_time_end",sdf.parse(complete_time_end));
        }
        if ( !plan_time_start.equals("")){
            map.put("plan_time_start",sdf.parse(plan_time_start));
        }
        if ( !plan_time_end.equals("")){
            map.put("plan_time_end",sdf.parse(plan_time_end));
        }
        System.out.println(map);
        List<Project> projects = projectDao.selectProjectByCondition (map);
        Integer total = projectDao.selectTotalProject(map);
        Map<String,Object> map1 = new HashMap<>();
        map1.put("total",total);
        List<Object> list = new ArrayList<>();
        list.add(map1);
        list.add(projects);
        return rs = new Result("200",null,list);
    }

    /**
     * 根据项目id获取项目信息
     * @param project_id
     * @return
     */
    @Override
    public Result findProjectById(Integer project_id) {
        Project project =projectDao.findProjectById(project_id);
        rs = new Result("200",null,project);
        return rs;
    }


}
