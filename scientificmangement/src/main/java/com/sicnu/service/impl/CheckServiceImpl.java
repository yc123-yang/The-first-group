package com.sicnu.service.impl;

import com.sicnu.mapper.*;
import com.sicnu.pojo.*;
import com.sicnu.service.CheckService;
import com.sicnu.util.Result;
import com.sun.mail.imap.protocol.INTERNALDATE;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CheckServiceImpl implements CheckService {
    @Resource
    UserMapper userMapper;
    @Resource
    AwardMapper awardMapper;
    @Resource
    BookMapper bookMapper;
    @Resource
    PaperMapper paperMapper;
    @Resource
    PatentMapper patentMapper;
    @Resource
    ProjectMapper projectMapper;
    @Resource
    CheckMapper checkMapper;
    private Result rs;
    @Override
    public Result finalCheck(String checkTime) {
        checkMapper.delCheck();
        List<Object> list = new ArrayList<>();
        List<Integer> userIds = userMapper.selectAllUserId();
        String[] str = checkTime.split("年");
        String start_time =null;
        String end_time = null;
        if (str[1].equals("第1学期")){
            start_time = str[0]+"-01-01 00:00:00";
            end_time = str[0]+"-06-30 00:00:00";
        }else{
            start_time = str[0]+"-07-01 00:00:00";
            end_time = str[0]+"-12-31 00:00:00";
        }


        for (Integer userId : userIds) {
            Check check =  new Check();
            Map<String,Object> map= new HashMap<>();
            map.put("start_time",start_time);
            map.put("end_time",end_time);
            map.put("leader_id",userId);
            //用户信息
            User user = userMapper.findUserById(userId);
            check.setName(user.getUser_name());
            check.setDepartment_id(user.getDepartment_id());
            //用户获奖情况考核
            Integer awardCount = awardMapper.selectCountAward(map);
            Integer awardGrade = awardGrade(userId);
            check.setAward_count(awardCount);
            //用户著作情况考核

            Integer bookCount = bookMapper.selectCountBook(map);
            Integer bookGrade = bookGrade(userId);
            check.setBook_count(bookCount);
            //用户论文情况考核
            Integer paperCount = paperMapper.selectCountPaper(map);
            Integer paperGrade = paperCount*7;
            check.setPaper_count(paperCount);
            //用户专利情况考核
            Integer patentCount = patentMapper.selectCountPatent(map);
            Integer patentGrade = patentCount*7;
            check.setPatent_count(patentCount);

            Integer projectCount = projectMapper.selectCountProject(map);
            Integer projectGrade = projectGrade(userId);
            check.setProject_count(projectCount);

            Integer totalGrade =awardGrade+bookGrade+paperGrade+patentGrade+projectGrade;
            check.setTotal_grade(totalGrade);
            check.setCheck_time(checkTime);
            checkMapper.addCheck(check);
        }
        List<Check> checks = checkMapper.selectCheckByCondition();
        rs = new Result("200",null,checks);
        return rs;
    }

    public int projectGrade(int id){
        int grade = 0;
        List<Project> projects = projectMapper.findProjectByLeaderId(id);
        for (Project project : projects) {
            if (project.getLevel_id()==1){
                grade+=3;
            }else if (project.getLevel_id()==2){
                grade +=5;
            }else{
                grade+=10;
            }
        }
        return grade;
    }

    public int awardGrade(int id){
        int grade = 0;
        List<Award> awards = awardMapper.findAwardByLeaderId(id);
        for (Award award : awards) {
            if (award.getAl_id()==1){
                grade+=3;
            }else if (award.getAl_id()==2){
                grade+=5;
            }else {
                grade+=10;
            }
        }
        return grade;
    }
    public int bookGrade(int id){
        int grade = 0;
        List<Book> books = bookMapper.findBookByLeaderId(id);
        for (Book book : books) {
            if (book.getPl_id()==1){
                grade+=3;
            }else if (book.getPl_id()==2){
                grade+=5;
            }else {
                grade+=10;
            }
        }
        return grade;
    }

    public static void main(String[] args) {
        String checkTime = "2018年第1学期";
        String[] str = checkTime.split("年");
        if (str[1].equals("第1学期")){
            str[0] = str[0]+"-01-01 00:00:00";
        }else{
            str[0] = str[0]+"-07-01 00:00:00";
        }
        System.out.println(str[0]);
    }
}
