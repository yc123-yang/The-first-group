package com.sicnu;

import com.sicnu.dao.UserDao;
import com.sicnu.pojo.User;
import com.sicnu.service.impl.UserSerciceImpl;
import com.sicnu.util.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;
import java.io.File;
import java.util.List;

//测试方法用的  不用管
@RunWith(SpringRunner.class)
@SpringBootTest
public class ScientificmangementApplicationTests {

    @Autowired
    UserDao userdao;
//    @Autowired
//    JavaMailSenderImpl mailSender;

    @Autowired
    UserSerciceImpl userSercice;

    @Resource
    DataSource dataSource;

    @Test
    public void contextLoads() {
        System.out.println(dataSource.getClass());
        userdao.updatePwd("root","admin","1776557392@qq.com","2018110354","511528199900000000");
//        System.out.println(users);
//        Result rs = userSercice.addUser("admin","admin123","1","1","1","1",1,1);
//        Result rs =userSercice.findByName("root","adm in123");
//       SimpleMailMessage mailMessage =  new SimpleMailMessage();
//       mailMessage.setSubject("杨春你好");
//       mailMessage.setText("加油呀");
//       mailMessage.setTo("1776557392@qq.com");
//       mailMessage.setFrom("1776557392@qq.com");
//       mailSender.send(mailMessage);
//        System.out.println(rs);
    }
//    @Test
//    public void contextLoads2() throws MessagingException {
//        MimeMessage mimeMailMessage = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(mimeMailMessage,true);
//        helper.setSubject("杨春你好");
//        helper.setText("<p style ='color:red'>yc</p>",true);
////        helper.addAttachment("1.jpg",new File());
//    }
//
//    @Test
//    public void find(){
//        System.out.println(userdao.findByName("root"));
//    }
}
