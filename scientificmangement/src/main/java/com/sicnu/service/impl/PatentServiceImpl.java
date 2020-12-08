package com.sicnu.service.impl;

import com.sicnu.mapper.*;
import com.sicnu.pojo.CacheUser;
import com.sicnu.pojo.Paper;
import com.sicnu.pojo.Patent;
import com.sicnu.pojo.User;
import com.sicnu.pojo.teamExamine.PatentTeamExamine;
import com.sicnu.pojo.teamMap.PaperTeamMap;
import com.sicnu.pojo.teamMap.PatentTeamMap;
import com.sicnu.pojo.teamMap.UserAuth;
import com.sicnu.service.PatentService;
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

/**
 * 专利
 */
@Service
public class PatentServiceImpl implements PatentService {
    @Resource
    PatentMapper patentMapper;

    private Result rs = null;

    @Resource
    UserMapper userDao;

    @Resource
    PatentTeamExamineMapper patentTeamExamineMapper;
    @Resource
    PatentExamineMapper patentExamineMapper;
    @Resource
    JavaMailSenderImpl mailSender;
    @Resource
    PatentTeamMapper patentTeamMapper;
    @Resource
    CacheUserMapper cacheUserMapper;
    @Resource
    RoleAuthMapper roleAuthMapper;

    /**
     * 添加专利
     *
     * @param patent:专利申请信息
     * @return
     */
    @Override
    public Result addPatent(Patent patent,String checkMessage,String message) {
        try {
            Integer patentExamineId = patentExamineMapper.selectPatentExamineId(patent.getLeader_id(), patent.getPatent_name());
            List<PatentTeamExamine> patentTeamExamines = patentTeamExamineMapper.selectPatentTeamExamineById(patentExamineId);

            //获取项目负责人信息
            User user = userDao.findUserById(patent.getLeader_id());
            //创建邮件环境，反馈信息
            MimeMessage mailMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage);

            //如果不通过审核反馈
            if (checkMessage.equals("fail")) {
                helper.setSubject("高校科研管理系统注册验证码");
                helper.setText("<p>您的项目申报审核未通过，因为<span style='color:blue;text-decoration:underline'>" + message + "</span>,请您解决之后重新申请。</p>", true);
                //负责人邮箱
                helper.setTo(user.getUser_email());
                helper.setFrom("1776557392@qq.com");
                mailSender.send(mailMessage);

                //从待审核里面删除
                patentTeamExamineMapper.delPatentTeamExamineTeam(patentExamineId);
                patentExamineMapper.delPatentExamine(patent.getLeader_id(),patent.getPatent_name());
                rs = new Result("400", "审核结果已反馈", null);
            } else {
                patentMapper.addPatent(patent);
                //获取项目id 返给用户
                Integer patentId = patentMapper.selectPatentId(patent.getLeader_id(), patent.getPatent_name());
                helper.setSubject("高校科研管理系统注册验证码");
                helper.setText("<p>您的项目申报审核成功，项目编号为：<span style='color:blue;text-decoration:underline'>" + patent + "</span>,请勿遗忘。</p>", true);
                helper.setTo(user.getUser_email());
                helper.setFrom("1776557392@qq.com");
                mailSender.send(mailMessage);
                for (PatentTeamExamine patentTeamExamine : patentTeamExamines) {
                    patentTeamMapper.addPatentTeamUser(patentId,patentTeamExamine.getUser_id(),patentTeamExamine.getContribution());
                }
                //从待审核删除
                patentTeamExamineMapper.delPatentTeamExamineTeam(patentExamineId);
                patentExamineMapper.delPatentExamine(patent.getLeader_id(),patent.getPatent_name());
                rs = new Result("200", "审核结果已反馈", null);
            }
//            Patent patent1 = patentMapper.selectPatentByNumber(patent.getApplication_number(), patent.getPublic_number(), patent.getAuthorization_number());
//            if (patent1 != null) {
//                rs = new Result("401", "该专利编号已经存在", null);
//            } else {
//                patentMapper.addPatent(patent);
//                rs = new Result("200", "专利注册成功", null);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 根据条件查询专利
     *
     * @param patent
     * @param application_time_start
     * @param application_time_end
     * @param public_time_start
     * @param public_time_end
     * @param authorization_time_start
     * @param authorization_time_end
     * @param pageSize
     * @param pageNum
     * @return
     * @throws ParseException
     */
    @Override
    public Result selectPatentByCondition(Patent patent, String application_time_start, String application_time_end, String public_time_start, String public_time_end, String authorization_time_start, String authorization_time_end, Integer pageSize, Integer pageNum) throws ParseException {
        List<Object> list = null;
        try {
            Map<String, Object> map = new HashMap<>();

            //获取登陆用户的缓存信息
            List<CacheUser> cacheUsers = cacheUserMapper.findAllCacheUser();
            //获取登录用户的id
            Integer uid = cacheUsers.get(0).getUser_id();

            map.put("patent_name", patent.getPatent_name());
            map.put("pt_id", patent.getPt_id());
            map.put("pr_id", patent.getPr_id());
            map.put("ps_id", patent.getPs_id());
            map.put("aod_id", patent.getAod_id());
            map.put("application_number", patent.getApplication_number());
            map.put("public_number", patent.getPublic_number());
            map.put("authorization_number", patent.getApplication_number());
            map.put("pageNum", pageNum);
            map.put("pageSize", pageSize);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!application_time_start.equals("")) {
                map.put("application_time_start", sdf.parse(application_time_start));
            }
            if (!application_time_end.equals("")) {
                map.put("application_time_end", sdf.parse(application_time_end));
            }
            if (!public_time_start.equals("")) {
                map.put("public_time_start", sdf.parse(public_time_start));
            }
            if (!public_time_end.equals("")) {
                map.put("public_time_end", sdf.parse(public_time_end));
            }
            if (!authorization_time_start.equals("")) {
                map.put("authorization_time_start", sdf.parse(authorization_time_start));
            }
            if (!authorization_time_end.equals("")) {
                map.put("authorization_time_end", sdf.parse(authorization_time_end));
            }
            User user = userDao.findUserById(uid);
            List<UserAuth> userAuths = roleAuthMapper.findUserAuth(user.getRole_id());
            int cnt =0;
            for (UserAuth userAuth : userAuths) {
                if (userAuth.getAuth_resource().equals("/allPatent")){
                    cnt=1;
                }
            }
            if (cnt==1){
                Integer total = patentMapper.selectTotalPatent(map);
                List<Patent> patents = patentMapper.selectPatentByCondition(map);
                Map<String, Object> map1 = new HashMap<>();
                map1.put("total", total);
                list = new ArrayList<>();
                list.add(map1);
                list.add(patents);
            }else {
                map.put("leader_id", uid);

                Integer total = patentMapper.selectTotalPatent(map);
                List<Patent> patents = patentMapper.selectPatentByCondition(map);
                Map<String, Object> map1 = new HashMap<>();
                map1.put("total", total);
                list = new ArrayList<>();
                list.add(map1);
                list.add(patents);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs = new Result("200", null, list);
    }

    /**
     * 更新专利
     *
     * @param patent
     * @return
     */
    @Override
    public Result updatePatent(Patent patent) {
        try {
            patentMapper.updatePatent(patent);
            rs = new Result("200", "修改成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 删除
     *
     * @param patent_id
     * @return
     */
    @Override
    public Result delPatentById(Integer patent_id) {
        try {
            patentMapper.delPatentById(patent_id);
            rs = new Result("200", "删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result selectAllPatentByCondition(Patent patent, String application_time_start, String application_time_end, String public_time_start, String public_time_end, String authorization_time_start, String authorization_time_end, Integer pageSize, Integer pageNum) throws ParseException {
        List<Object> list = null;
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("patent_name", patent.getPatent_name());
            map.put("pt_id", patent.getPt_id());
            map.put("pr_id", patent.getPr_id());
            map.put("ps_id", patent.getPs_id());
            map.put("aod_id", patent.getAod_id());
            map.put("application_number", patent.getApplication_number());
            map.put("public_number", patent.getPublic_number());
            map.put("authorization_number", patent.getApplication_number());
            map.put("pageNum", pageNum);
            map.put("pageSize", pageSize);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!application_time_start.equals("")) {
                map.put("application_time_start", sdf.parse(application_time_start));
            }
            if (!application_time_end.equals("")) {
                map.put("application_time_end", sdf.parse(application_time_end));
            }
            if (!public_time_start.equals("")) {
                map.put("public_time_start", sdf.parse(public_time_start));
            }
            if (!public_time_end.equals("")) {
                map.put("public_time_end", sdf.parse(public_time_end));
            }
            if (!authorization_time_start.equals("")) {
                map.put("authorization_time_start", sdf.parse(authorization_time_start));
            }
            if (!authorization_time_end.equals("")) {
                map.put("authorization_time_end", sdf.parse(authorization_time_end));
            }
            System.out.println(map);
            Integer total = patentMapper.selectTotalPatent(map);
            List<Patent> patents = patentMapper.selectPatentByCondition(map);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("total", total);
            list = new ArrayList<>();
            list.add(map1);
            list.add(patents);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs = new Result("200", null, list);
    }

    @Override
    public Result findPatentById(Integer patent_id) {
        try {
            Patent patent = patentMapper.findPatentById(patent_id);
            rs = new Result("200",null,patent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public  Result findPersonalPatentMessage(Integer patent_id){
        try {
            Patent patent = patentMapper.findPatentById(patent_id);
            List<PatentTeamMap> paperTeamMaps = patentTeamMapper.selectPatentTeam(patent_id);
            Map<String,Object> map =new HashMap<>();
            map.put("patent",patent);
            map.put("paperTeamMaps",paperTeamMaps);
            rs = new Result("200",null,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
