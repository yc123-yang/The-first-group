package com.sicnu.service.impl;

import com.sicnu.mapper.*;
import com.sicnu.pojo.Award;
import com.sicnu.pojo.Book;
import com.sicnu.pojo.Project;
import com.sicnu.pojo.User;
import com.sicnu.service.CheckService;
import com.sicnu.util.Result;
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

    private Result rs;
    @Override
    public Result finalCheck() {
        List<Object> list = new ArrayList<>();
        List<Integer> userIds = userMapper.selectAllUserId();
        for (Integer userId : userIds) {
            Map<String,Object> map= new HashMap<>();
            //用户信息
            User user = userMapper.findUserById(userId);
            map.put("department",user.getDepartment_id());
            map.put("name",user.getUser_name());
            //用户获奖情况考核
            Integer awardCount = awardMapper.selectCountAward(userId);
            map.put("awardCount",awardCount);
            Integer awardGrade = awardGrade(userId);
            //用户著作情况考核
            Integer bookCount = bookMapper.selectCountBook(userId);
            map.put("bookCount",bookCount);
            Integer bookGrade = bookGrade(userId);
            //用户论文情况考核
            Integer paperCount = paperMapper.selectCountPaper(userId);
            map.put("paperCount",paperCount);
            Integer paperGrade = paperCount*7;
            //用户专利情况考核
            Integer patentCount = patentMapper.selectCountPatent(userId);
            map.put("patentCount",patentCount);
            Integer patentGrade = patentCount*7;

            Integer projectCount = projectMapper.selectCountProject(userId);
            map.put("projectCount",projectCount);
            Integer projectGrade = projectGrade(userId);

            map.put("totalGrade",awardGrade+bookGrade+paperCount+patentGrade+projectGrade);
            list.add(map);
        }
        rs = new Result("200",null,list);
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
}
