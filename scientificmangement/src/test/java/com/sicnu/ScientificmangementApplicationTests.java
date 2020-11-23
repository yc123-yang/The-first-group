package com.sicnu;


import com.sicnu.mapper.*;
import com.sicnu.pojo.Project;
import com.sicnu.pojo.ReviewProject;
import com.sicnu.service.impl.AuthServiceImpl;
import com.sicnu.service.impl.CheckEmailServiceImpl;
import com.sicnu.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.*;


//测试方法用的  不用管
@RunWith(SpringRunner.class)
@SpringBootTest
public class ScientificmangementApplicationTests {

    @Resource
    ReviewProjectMapper reviewProjectMapper;
    @Resource
    ProjectMapper projectDao;
    @Resource
    CheckEmailServiceImpl check_emailService;
    @Resource
    CheckEmailMapper check_emailDao;
    @Resource
    UserMapper userDao;
    @Resource
    UserServiceImpl userService;
    @Resource
    AuthServiceImpl authService;
    @Resource
    ProjectTeamMapper teamMapper;
    @Resource
    CacheUserMapper cacheUserMapper;
    @Test
    public void contextLoads() throws MessagingException {
//        List<Project> project = projectDao.findAllProject();
//        System.out.println(project);
        Date date = new Date();
//        projectDao.addProject("good","good",2018110354,"yy","a","a",new Date(date.getTime()));
//        project_teamDao.delTeamUser(1);
//        Result rs =  check_teamService.checkProjectTeam(3,1,date);
//        check_teamDao.delCheckTeamUser(1);
//        check_emailDao.delCode(310905);
//        System.out.println(rs.getData());
//        Result rs = userSercice.addUser("root123","admin123","yc","1776557392@qq.com","2018110399","5115281999900000001",1,11110);
//        System.out.println(rs);
//        List<Project> projects = projectDao.selectAllTeam();
//        System.out.println(projects);

//        Integer a = project_teamDao.selectNumberById(2);
//        System.out.println(2);
//       check_emailService.delCode(789022);
//        System.out.println(rs);
        Project project = new Project();
        ReviewProject reviewProject = new ReviewProject();
        project.setProject_name("yr");
        project.setLeader_id(3);
        project.setDepartment_id(2);
        project.setAod_id(1);
        project.setSc_id(1);
        project.setSubject_id(1);
        project.setNature_id(1);
        project.setLevel_id(1);
        project.setStatus_id(1);
        project.setSd_id(1);
        project.setAt_id(1);
        project.setApproval_number("yy");
        project.setStart_time(new Date());
        project.setPlan_time(new Date());
        project.setComplete_time(new Date());
        project.setOutlay(10000);
        project.setCt_id(1);
        reviewProject.setProject_name("yr");
        reviewProject.setLeader_id(3);
        reviewProject.setDepartment_id(2);
        reviewProject.setAod_id(1);
        reviewProject.setSc_id(1);
        reviewProject.setSubject_id(1);
        reviewProject.setNature_id(1);
        reviewProject.setLevel_id(1);
        reviewProject.setStatus_id(1);
        reviewProject.setSd_id(1);
        reviewProject.setAt_id(1);
        reviewProject.setApproval_number("yy");
        reviewProject.setStart_time(new Date());
        reviewProject.setPlan_time(new Date());
        reviewProject.setComplete_time(new Date());
        reviewProject.setOutlay(10000);
        reviewProject.setCt_id(1);
//        projectDao.addProject(project);
//        List<Project> projects = projectDao.selectProject(project);
//        System.out.println(projects);
//        projectDao.delProject(1);
//        projectDao.updateProject(project);
//        Integer pid = projectDao.selectProjectId(3,"yr");
//        System.out.println(pid);
//        reviewProjectMapper.addReviewProject(reviewProject);
//        List<ReviewProject> reviewProjects =reviewProjectMapper.selectReviewProject();
//        System.out.println(reviewProjects);
//        List<Project> projects = projectDao.selectTeamByPid(2);
//        List<Object> list = new ArrayList<>();
//        for (int i = 0; i <projects.size(); i++) {
//            Map<String,Object> map = new HashMap<String, Object>();
//            map.put("name",projects.get(i).getProjectTeams().get(0).getUsers().get(0).getUser_name());
//            map.put("role_id",projects.get(i).getProjectTeams().get(0).getUsers().get(0).getRole_id());
//            map.put("user_status",projects.get(i).getProjectTeams().get(0).getUser_role());
//            map.put("depart_id",projects.get(i).getProjectTeams().get(0).getUsers().get(0).getDepartment_id());
//            list.add(map);
//
//        }
//        for (Project project1 : projects) {
//            map.put("name",project1.getProjectTeams().get(0).getUsers().get(0).getUser_name());
//            map.put("role_id",project1.getProjectTeams().get(0).getUsers().get(0).getRole_id());
//            map.put("user_status",project1.getProjectTeams().get(0).getUser_role());
//            map.put("depart_id",project1.getProjectTeams().get(0).getUsers().get(0).getDepartment_id());
//            list.add(map);
//            map.clear();
//        }

//        cacheUserMapper.delCacheUser();
//        System.out.println(cacheUsers);
//        System.out.println(list);
//        List<Object> auths = authService.getAuth(1);
//        System.out.println(auths);
        List<Integer> projectIds = teamMapper.selectProject_id(0);
        System.out.println(projectIds);
    }

}
