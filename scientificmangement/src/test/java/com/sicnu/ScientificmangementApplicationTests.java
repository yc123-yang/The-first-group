package com.sicnu;


import com.sicnu.dao.ProjectDao;
import com.sicnu.pojo.Project;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;


//测试方法用的  不用管
@RunWith(SpringRunner.class)
@SpringBootTest
public class ScientificmangementApplicationTests {

    @Resource
    ProjectDao projectDao;

    @Test
    public void contextLoads() {
        List<Project> project = projectDao.selectProjectById("yy");
        System.out.println(project);
    }

}
