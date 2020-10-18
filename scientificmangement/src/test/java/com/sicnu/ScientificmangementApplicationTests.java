package com.sicnu;


import com.sicnu.dao.ProjectDao;
import com.sicnu.dao.Project_TeamDao;
import com.sicnu.dao.UserDao;
import com.sicnu.pojo.Project;
import com.sicnu.service.impl.UserSerciceImpl;
import com.sicnu.util.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;


//测试方法用的  不用管
@RunWith(SpringRunner.class)
@SpringBootTest
public class ScientificmangementApplicationTests {

    @Resource
    UserDao userDao;
    @Resource
    UserSerciceImpl  userSercice;
    @Resource
    ProjectDao projectDao;
    @Resource
    Project_TeamDao project_teamDao;

    @Test
    public void contextLoads() throws MessagingException {
//        List<Project> project = projectDao.findAllProject();
//        System.out.println(project);
        Date date = new Date();
//        projectDao.addProject("good","good",2018110354,"yy","a","a",new Date(date.getTime()));
//        project_teamDao.delTeamUser(1);
//        project_teamDao.delProjectTeam(1);
        Result rs = userSercice.findByName("root","admin123");
        System.out.println(rs);
    }

}
