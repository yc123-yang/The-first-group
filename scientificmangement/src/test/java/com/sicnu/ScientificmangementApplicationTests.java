package com.sicnu;


import com.sicnu.dao.*;
import com.sicnu.service.impl.CheckEmailServiceImpl;
import com.sicnu.service.impl.CheckTeamServiceImpl;
import com.sicnu.service.impl.UserSerciceImpl;
import com.sicnu.util.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.Date;


//测试方法用的  不用管
@RunWith(SpringRunner.class)
@SpringBootTest
public class ScientificmangementApplicationTests {

    @Resource
    CheckTeamServiceImpl check_teamService;

    @Resource
    CheckTeamDao check_teamDao;
    @Resource
    CheckEmailServiceImpl check_emailService;
    @Resource
    CheckEmailDao check_emailDao;
    @Resource
    UserDao userDao;
    @Resource
    UserSerciceImpl  userSercice;
    @Resource
    ProjectDao projectDao;
    @Resource
    ProjectTeamDao project_teamDao;

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
        Result rs = userSercice.addUser("root123","admin123","yc","1776557392@qq.com","2018110399","5115281999900000001",1,11110);
//        System.out.println(rs);
//        List<Project> projects = projectDao.selectAllTeam();
//        System.out.println(projects);

//        Integer a = project_teamDao.selectNumberById(2);
//        System.out.println(2);
//       check_emailService.delCode(789022);
//        System.out.println(rs);
    }

}
