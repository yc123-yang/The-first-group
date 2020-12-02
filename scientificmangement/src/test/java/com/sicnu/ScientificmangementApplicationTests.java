package com.sicnu;



import com.sicnu.pojo.PatentExamine;
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
    AwardExamineServiceImpl awardExamineService;

    @Resource
    BookExamineServiceImpl bookExamineService;

    @Resource
    PaperExamineServiceImpl paperExamineService;

    @Resource
    PatentExamineServiceImpl patentExamineService;
    @Test
    public void contextLoads() throws MessagingException {

        PatentExamine patentExamine = new PatentExamine();
//        bookExamine.setAchievement_name("1");
        patentExamine.setLeader_id(1);
        patentExamine.setPatent_name("2");
//        paperExamine.setPress("1");
        patentExamine.setPt_id(1);
//        paperExamine.setLanguage_id(1);
        patentExamine.setPr_id(1);
        patentExamine.setPs_id(1);
        patentExamine.setAod_id(1);
//        patentExamine.setPt_id(1);
//        paperExamine.setBt_id(1);
//        paperExamine.setIsbn("1");
        patentExamine.setApplication_number("1");
        patentExamine.setApplication_time(new Date());
        patentExamine.setPublic_number("1");
        patentExamine.setPublic_time(new Date());
        patentExamine.setAuthorization_number("1");
        patentExamine.setAuthorization_time(new Date());

//        paperExamine.setRt_id(1);
//        paperExamine.setTrans("1");
//        patentExamine.setPublish_time(new Date());
        patentExamine.setExamine_status("1");
        patentExamine.setApply_time(new Date());
        patentExamine.setReviewer_id(1);
//        patentExamine.setRemark("1");
        Integer[] user_id = {1,2,3};
        String[] user_role = {"1","2","3"};
        Double[] contribution = {1.00,2.00,3.00};
        patentExamineService.addPatentExamine(patentExamine,user_id,user_role,contribution);
    }

}
