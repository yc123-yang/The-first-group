package com.sicnu;



import com.sicnu.mapper.AwardTeamExamineMapper;
import com.sicnu.pojo.PatentExamine;
import com.sicnu.pojo.ProjectExamine;
import com.sicnu.pojo.teamMap.AwardTeamMap;
import com.sicnu.service.ProjectExamineService;
import com.sicnu.service.impl.AwardExamineServiceImpl;
import com.sicnu.service.impl.BookExamineServiceImpl;
import com.sicnu.service.impl.PaperExamineServiceImpl;
import com.sicnu.service.impl.PatentExamineServiceImpl;
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
    AwardTeamExamineMapper awardTeamExamineMapper;
     @Resource
    AwardExamineServiceImpl awardExamineService;

    @Resource
    BookExamineServiceImpl bookExamineService;

    @Resource
    PaperExamineServiceImpl paperExamineService;
    @Resource
    ProjectExamineService projectExamineService;
    @Resource
    PatentExamineServiceImpl patentExamineService;
    @Test
    public void contextLoads() throws MessagingException {

        ProjectExamine projectExamine = new ProjectExamine();

        projectExamine.setProject_name("yc");
        projectExamine.setLeader_id(1);
        projectExamine.setDepartment_id(2);
        projectExamine.setAod_id(1);
        projectExamine.setSc_id("1");
        projectExamine.setSubject_id("1");
        projectExamine.setNature_id(1);
        projectExamine.setLevel_id(1);
        projectExamine.setStatus_id(1);
        projectExamine.setSd_id(1);
        projectExamine.setAt_id(1);
        projectExamine.setApproval_number("110");
        projectExamine.setStart_time(new Date());
        projectExamine.setPlan_time(new Date());
        projectExamine.setComplete_time(new Date());
        projectExamine.setOutlay(1);
        projectExamine.setCt_id(1);
        projectExamine.setExamine_status("111");
        projectExamine.setApply_time(new Date());
        projectExamine.setReviewer_id(1);

        Integer[] user_id = {1,2,3};
        String[] team_role = {"1","2","3"};
//        Double[] contribution = {1.00,2.00,3.00};
//        projectExamineService.addProjectExamine(projectExamine,user_id,team_role);
       List<AwardTeamMap> awardTeamMaps =awardTeamExamineMapper.selectAwardTeamExamineUser(28);
        System.out.println(awardTeamMaps.get(0).getName());
    }

}
