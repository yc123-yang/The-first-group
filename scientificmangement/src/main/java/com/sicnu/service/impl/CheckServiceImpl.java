package com.sicnu.service.impl;

import com.sicnu.mapper.*;
import com.sicnu.pojo.*;
import com.sicnu.service.CheckService;
import com.sicnu.util.Result;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Resource
    LevelMapper levelMapper;
    @Resource
    AwardLevelMapper awardLevelMapper;
    @Resource
    AwardRankMapper awardRankMapper;
    @Resource
    PeriodicalMapper periodicalMapper;
    @Resource
    PressLevelMapper pressLevelMapper;
    @Resource
    HttpSession session;
    private Result rs;

    @Scheduled(cron = "* * * 30 6,12 ? ")
    public void addAllFinalCheck() {

        try {
            checkMapper.delCheck();
            String start_time =null;
            String end_time = null;

            Date date = new Date();//当前日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化对象
            Calendar calendar = Calendar.getInstance();//日历对象
            calendar.setTime(date);//设置当前日期
            end_time = sdf.format(calendar.getTime());
            System.out.println(sdf.format(calendar.getTime()));
            calendar.add(Calendar.MONTH, -6);//月份减6
            start_time = sdf.format(calendar.getTime());
            System.out.println(sdf.format(calendar.getTime()));//输出格式化的日期
            String[] str = start_time.split("-");
            int a = Integer.parseInt(str[1]);
            String checkTime;
            if (a==6){
                checkTime = str[0] + "年第一学期";
            }else{
                checkTime = str[0] + "年第二学期";

            }
            List<Integer> userIds = userMapper.selectAllUserId();
            for (Integer userId : userIds) {
                Check check =  new Check();

                Map<String,Object> map= new HashMap<>();
                map.put("start_time",start_time);
                map.put("end_time",end_time);
                map.put("leader_id",userId);
                //用户信息
                 User user = userMapper.findUserById(userId);
                check.setUser_id(userId);
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
                Integer paperGrade = paperGrade(userId);
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



        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Result selectAllCheckByCondition(Integer user_id,Integer department_id,String check_time) {
        try {
            Map<String,Object> map = new HashMap<>();
            map.put("user_id",user_id);
            map.put("department_id",department_id);
            map.put("check_time",check_time);
            List<Check> checks = checkMapper.selectCheckByCondition(map);
            rs = new Result("200",null,checks);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }


    public Result selectPersonalCheckByCondition(Integer department_id,String check_time) {
        try {
            User user = (User)session.getAttribute("user");
            //获取登陆用户的缓存信息
//            List<CacheUser> cacheUsers = cacheUserMapper.findAllCacheUser();
            //获取登录用户的id
            Integer uid = user.getUser_id();
            Map<String,Object> map = new HashMap<>();
            map.put("user_id",uid);
            map.put("department_id",department_id);
            map.put("check_time",check_time);
            List<Check> checks = checkMapper.selectCheckByCondition(map);
            rs = new Result("200",null,checks);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }



    //项目成绩
    public int projectGrade(int id){
        int grade = 0;
        List<Project> projects = projectMapper.findProjectByLeaderId(id);
        for (Project project : projects) {
            grade +=levelMapper.selectLevelScoreById(project.getLevel_id());
        }
        return grade;
    }

    public int awardGrade(int id){
        int grade = 0;
        List<Award> awards = awardMapper.findAwardByLeaderId(id);
        for (Award award : awards) {
            grade+=awardLevelMapper.selectAwardLevelScoreById(award.getAl_id())+awardRankMapper.selectAwardRankScoreById(award.getAr_id());
        }
        return grade;
    }
    public int bookGrade(int id){
        int grade = 0;
        List<Book> books = bookMapper.findBookByLeaderId(id);
        for (Book book : books) {
            grade =pressLevelMapper.selectPressLevelScoreById(book.getPl_id());
        }
        return grade;
    }
    public int paperGrade(int id){
        int grade = 0;
        List<Paper> papers = paperMapper.findPaperByLeaderId(id);
        for (Paper paper : papers) {
            grade = periodicalMapper.selectPeriodicalScoreById(paper.getPeriodical_id());
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
