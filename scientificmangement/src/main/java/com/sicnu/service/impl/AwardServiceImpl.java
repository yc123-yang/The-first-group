package com.sicnu.service.impl;

import com.sicnu.mapper.AwardExamineMapper;
import com.sicnu.mapper.AwardMapper;
import com.sicnu.mapper.CacheUserMapper;
import com.sicnu.mapper.UserMapper;
import com.sicnu.pojo.Award;
import com.sicnu.pojo.CacheUser;
import com.sicnu.pojo.User;
import com.sicnu.service.AwardService;
import com.sicnu.util.Result;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AwardServiceImpl implements AwardService {
    @Resource
    AwardMapper awardMapper;

    private Result rs;

    @Resource
    UserMapper userDao;

    @Resource
    AwardExamineMapper awardExamineMapper;
    @Resource
    JavaMailSenderImpl mailSender;
    @Resource
    CacheUserMapper cacheUserMapper;

    @Override
    public Result addAward(Award award,String checkMessage,String message) {
        try {
            //获取项目id 返给用户
            Integer awardId = awardMapper.selectAwardId(award.getLeader_id(), award.getAward_name());
            //获取项目负责人信息
            User user = userDao.findUserById(award.getLeader_id());
            //创建邮件环境，反馈信息
            MimeMessage mailMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage);
//            Award award1 = awardMapper.selectAwardByNumber(award.getApproval_number());

            //如果不通过审核反馈
            if (checkMessage.equals("fail")) {
                helper.setSubject("高校科研管理系统注册验证码");
                helper.setText("<p>您的项目申报审核未通过，因为<span style='color:blue;text-decoration:underline'>" + message + "</span>,请您解决之后重新申请。</p>", true);
                //负责人邮箱
                helper.setTo(user.getUser_email());
                helper.setFrom("1776557392@qq.com");
                mailSender.send(mailMessage);
                //从待审核里面删除
                awardExamineMapper.delAwardExamine(award.getLeader_id(),award.getAward_name());
                rs = new Result("400", "审核结果已反馈", null);
            } else {
                awardMapper.addAward(award);
                helper.setSubject("高校科研管理系统注册验证码");
                helper.setText("<p>您的项目申报审核成功，项目编号为：<span style='color:blue;text-decoration:underline'>" + awardId + "</span>,请勿遗忘。</p>", true);
                helper.setTo(user.getUser_email());
                helper.setFrom("1776557392@qq.com");
                mailSender.send(mailMessage);
                //从待审核删除
                awardExamineMapper.delAwardExamine(award.getLeader_id(),award.getAward_name());
                rs = new Result("200", "审核结果已反馈", null);
            }
            //            if (award1 != null) {
//                rs = new Result("400","该奖励已经存在，不能再次录入系统",null);
//            } else {
//                awardMapper.addAward(award);
//                rs = new Result("200", "添加成功", null);
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result selectAwardByCondition(Award award, String award_time_start, String award_time_end, Integer pageSize, Integer pageNum) throws ParseException {

        List<Object> list = null;
        try {
            Map<String, Object> map = new HashMap<>();

            //获取登陆用户的缓存信息
            List<CacheUser> cacheUsers = cacheUserMapper.findAllCacheUser();
            //获取登录用户的id
            Integer uid = cacheUsers.get(0).getUser_id();
            map.put("achievement_name", award.getAchievement_name());
            map.put("leader_id", uid);
            map.put("award_name", award.getAward_name());
            map.put("issuing_authority", award.getIssuing_authority());
            map.put("approval_number", award.getApproval_number());
            map.put("aod_id", award.getAod_id());
            map.put("dr_id", award.getDr_id());
            map.put("ar_id", award.getAr_id());
            map.put("al_id", award.getAl_id());
            map.put("sc_id", award.getSc_id());
            map.put("subject_id", award.getSubject_id());
            map.put("sd_id", award.getSd_id());
            map.put("at_id", award.getAt_id());
            map.put("pageSize", pageSize);
            map.put("pageNum", pageNum);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!award_time_start.equals("")) {
                map.put("award_time_start", sdf.parse(award_time_start));
            }
            if (!award_time_end.equals("")) {
                map.put("award_time_end", sdf.parse(award_time_end));
            }
            System.out.println(map);
            List<Award> awards = awardMapper.selectAwardByCondition(map);
            Integer total = awardMapper.selectTotalAward(map);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("total", total);
            list = new ArrayList<>();
            list.add(map1);
            list.add(awards);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs = new Result("200", null, list);
    }

    @Override
    public Result updateAward(Award award) {
        System.out.println();
        awardMapper.updateAward(award);
        rs = new Result("200", "更新成功", null);
        return rs;
    }

    @Override
    public Result delAward(Integer award_id) {
        try {
            awardMapper.delAward(award_id);
            rs = new Result("200", "删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @Override
    public Result selectAllAwardByCondition(Award award, String award_time_start, String award_time_end, Integer pageSize, Integer pageNum) throws ParseException {
        List<Object> list = null;
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("achievement_name", award.getAchievement_name());
            map.put("award_name", award.getAward_name());
            map.put("issuing_authority", award.getIssuing_authority());
            map.put("approval_number", award.getApproval_number());
            map.put("aod_id", award.getAod_id());
            map.put("dr_id", award.getDr_id());
            map.put("ar_id", award.getAr_id());
            map.put("al_id", award.getAl_id());
            map.put("sc_id", award.getSc_id());
            map.put("subject_id", award.getSubject_id());
            map.put("sd_id", award.getSd_id());
            map.put("at_id", award.getAt_id());
            map.put("pageSize", pageSize);
            map.put("pageNum", pageNum);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!award_time_start.equals("")) {
                map.put("award_time_start", sdf.parse(award_time_start));
            }
            if (!award_time_end.equals("")) {
                map.put("award_time_end", sdf.parse(award_time_end));
            }
            System.out.println(map);
            List<Award> awards = awardMapper.selectAwardByCondition(map);
            Integer total = awardMapper.selectTotalAward(map);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("total", total);
            list = new ArrayList<>();
            list.add(map1);
            list.add(awards);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs = new Result("200", null, list);
    }
}
