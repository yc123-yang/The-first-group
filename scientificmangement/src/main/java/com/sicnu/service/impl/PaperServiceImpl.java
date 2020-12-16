package com.sicnu.service.impl;

import com.alibaba.fastjson.JSON;
import com.sicnu.mapper.*;
import com.sicnu.pojo.*;
import com.sicnu.pojo.teamExamine.BookTeamExamine;
import com.sicnu.pojo.teamExamine.PaperTeamExamine;
import com.sicnu.pojo.teamMap.BookTeamMap;
import com.sicnu.pojo.teamMap.PaperTeamMap;
import com.sicnu.pojo.teamMap.UserAuth;
import com.sicnu.service.PaperService;
import com.sicnu.util.Result;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaperServiceImpl implements PaperService {
    @Resource
    PaperMapper paperMapper;

    private Result rs;

    @Resource
    UserMapper userDao;

    @Resource
    JavaMailSenderImpl mailSender;
    @Resource
    PaperTeamExamineMapper paperTeamExamineMapper;
    @Resource
    PaperTeamMapper paperTeamMapper;
    @Resource
    PaperExamineMapper paperExamineMapper;
    @Resource
    CacheUserMapper cacheUserMapper;
    @Resource
    PeriodicalPaperExamineMapper periodicalPaperExamineMapper;
    @Resource
    PeriodicalPaperMapper periodicalPaperMapper;
    @Resource
    private RoleAuthMapper roleAuthMapper;
    @Resource
    private HttpSession session;
    /**
     * 添加论文
     *
     * @param paper
     * @return
     */
    @Override
    public Result addPaper(Paper paper,String checkMessage,String message,Integer[] periodicalIds) {
        try {
            Integer paperExamineId = paperExamineMapper.selectPaperExamineId(paper.getLeader_id(), paper.getPaper_name());
            List<PaperTeamExamine> paperTeamExamines = paperTeamExamineMapper.selectPaperTeamExamineById(paperExamineId);
            List<Integer> periodicalIdList = periodicalPaperExamineMapper.findPeriodicalExamineByPaperId(paperExamineId);
            //获取项目负责人信息
            User user = userDao.findUserById(paper.getLeader_id());

            //创建邮件环境，反馈信息
            MimeMessage mailMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage);

            //如果不通过审核反馈
            if (checkMessage.equals("fail")) {
                helper.setSubject("论文上传科研项目管理系统审核");
                helper.setText("<p>您的论文上传申报审核未通过，因为<span style='color:blue;text-decoration:underline'>" + message + "</span>,请您解决之后重新申请。</p>", true);
                //负责人邮箱
                helper.setTo(user.getUser_email());
                helper.setFrom("1776557392@qq.com");
                mailSender.send(mailMessage);
                //从待审核里面删除
                periodicalPaperExamineMapper.delPeriodicalExamineByPaperId(paperExamineId);
                paperTeamExamineMapper.delPaperTeamExamineTeam(paperExamineId);
                paperExamineMapper.delPaperExamine(paper.getLeader_id(),paper.getPaper_name());
                rs = new Result("400", "审核结果已反馈", null);
            } else {
                paperMapper.addPaper(paper);
                //获取项目id 返给用户
                Integer paperId = paperMapper.selectPaperId(paper.getLeader_id(), paper.getPaper_name());
                helper.setSubject("论文上传科研项目管理系统审核");
                helper.setText("<p>您的论文上传申报审核通过，项目编号为：<span style='color:blue;text-decoration:underline'>" + paperId + "</span>,请勿遗忘。</p>", true);
                helper.setTo(user.getUser_email());
                helper.setFrom("1776557392@qq.com");
                mailSender.send(mailMessage);
                for (PaperTeamExamine paperTeamExamine : paperTeamExamines) {
                    paperTeamMapper.addPaperTeamUser(paperId,paperTeamExamine.getUser_id(),paperTeamExamine.getContribution());
                }
                for (int i = 0; i < periodicalIdList.size(); i++) {
                    periodicalPaperMapper.addPeriodicalPaper(paperId, periodicalIdList.get(i));
                }
                //从待审核删除
                periodicalPaperExamineMapper.delPeriodicalExamineByPaperId(paperExamineId);
                paperTeamExamineMapper.delPaperTeamExamineTeam(paperExamineId);
                paperExamineMapper.delPaperExamine(paper.getLeader_id(),paper.getPaper_name());
                rs = new Result("200", "审核结果已反馈", null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 根据条件查找论文
     *
     * @param paper
     * @param publish_time_start
     * @param publish_time_end
     * @param pageNum
     * @param pageSize
     * @return
     * @throws ParseException
     */
    @Override
    public Result selectPaperByCondition(Paper paper, String publish_time_start, String publish_time_end, Integer pageNum, Integer pageSize) throws ParseException {
        List<Object> list = null;
        try {
            Map<String, Object> map = new HashMap<>();

            User user = (User) session.getAttribute("user");

            //获取登陆用户的缓存信息
            List<CacheUser> cacheUsers = cacheUserMapper.findAllCacheUser();
            //获取登录用户的id
            Integer uid = user.getUser_id();

            map.put("paper_name", paper.getPaper_name());
            map.put("pt_id", paper.getPt_id());
            map.put("periodical_id", paper.getPeriodical_id());
            map.put("publish_time", paper.getPublish_time());
            map.put("include_number", paper.getInclude_number());
            map.put("sc_id", paper.getSc_id());
            map.put("subject_id", paper.getSubject_id());
            map.put("aod_id", paper.getAod_id());
            map.put("sd_id", paper.getSd_id());
            map.put("remark", paper.getRemark());
            map.put("pageNum", pageNum);
            map.put("pageSize", pageSize);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!publish_time_start.equals("")) {
                map.put("publish_time_start", sdf.parse(publish_time_start));
            }
            if (!publish_time_end.equals("")) {
                map.put("publish_time_end", sdf.parse(publish_time_end));
            }

            System.out.println("map:"+ map);
//            User user = userDao.findUserById(uid);
            List<UserAuth> userAuths = roleAuthMapper.findUserAuth(user.getRole_id());
            int cnt =0;
            for (UserAuth userAuth : userAuths) {
                if (userAuth.getAuth_resource().equals("/papers/allPapers")){
                    cnt=1;
                }
            }

            if (cnt==1){
                List<Paper> papers = paperMapper.selectPaperByCondition(map);
                Integer total = paperMapper.selectTotalPaper(map);
                List<Map> mapList = new ArrayList<>();
                for (int i = 0; i < papers.size(); i++) {
                    Map temp = JSON.parseObject(JSON.toJSONString(papers.get(i)),Map.class);
                    temp.put("user_name",userDao.findUserById(papers.get(i).getLeader_id()).getUser_name());
                    temp.put("publish_time",sdf.format(papers.get(i).getPublish_time()));
                    mapList.add(temp);
                }
                Map<String, Object> map1 = new HashMap<>();
                map1.put("total", total);
                list = new ArrayList<>();
                list.add(map1);
                list.add(mapList);
            }else {
                map.put("leader_id", uid);
                List<Paper> papers = paperMapper.selectPaperByCondition(map);
                Integer total = paperMapper.selectTotalPaper(map);
                List<Map> mapList = new ArrayList<>();
                for (int i = 0; i < papers.size(); i++) {
                    Map temp = JSON.parseObject(JSON.toJSONString(papers.get(i)),Map.class);
                    temp.put("user_name",userDao.findUserById(papers.get(i).getLeader_id()).getUser_name());
                    temp.put("publish_time",sdf.format(papers.get(i).getPublish_time()));
                    mapList.add(temp);
                }
                Map<String, Object> map1 = new HashMap<>();
                map1.put("total", total);
                list = new ArrayList<>();
                list.add(map1);
                list.add(mapList);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs = new Result("200", null, list);
    }

    /**
     * 更新论文呢信息
     *
     * @param paper
     * @return
     */
    @Override
    public Result updatePaper(Paper paper) {
        try {
            paperMapper.updatePaper(paper);
            rs = new Result("200", "更新成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 删除论文
     *
     * @param paper_id
     * @return
     */
    @Override
    public Result delPaperById(Integer paper_id) {
        try {
            paperMapper.delPaperById(paper_id);
            rs = new Result("200", "删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @Override
    public Result selectAllPaperByCondition(Paper paper, String publish_time_start, String publish_time_end, Integer pageNum, Integer pageSize) throws ParseException {
        List<Object> list = null;
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("paper_name", paper.getPaper_name());
            map.put("pt_id", paper.getPt_id());
            map.put("periodical_id", paper.getPeriodical_id());
            map.put("publish_time", paper.getPublish_time());
            map.put("include_number", paper.getInclude_number());
            map.put("sc_id", paper.getSc_id());
            map.put("subject_id", paper.getSubject_id());
            map.put("aod_id", paper.getAod_id());
            map.put("sd_id", paper.getSd_id());
            map.put("remark", paper.getRemark());
            map.put("pageNum", pageNum);
            map.put("pageSize", pageSize);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!publish_time_start.equals("")) {
                map.put("publish_time_start", sdf.parse(publish_time_start));
            }
            if (!publish_time_end.equals("")) {
                map.put("publish_time_end", sdf.parse(publish_time_end));
            }

            Integer total = paperMapper.selectTotalPaper(map);
            List<Paper> papers = paperMapper.selectPaperByCondition(map);
            List<Map> mapList = new ArrayList<>();
            for (int i = 0; i < papers.size(); i++) {
                Map temp = JSON.parseObject(JSON.toJSONString(papers.get(i)),Map.class);
                temp.put("user_name",userDao.findUserById(papers.get(i).getLeader_id()).getUser_name());
                temp.put("publish_time",sdf.format(papers.get(i).getPublish_time()));
                mapList.add(temp);
            }
            Map<String, Object> map1 = new HashMap<>();
            map1.put("total", total);
            list = new ArrayList<>();
            list.add(map1);
            list.add(mapList);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs = new Result("200", null, list);
    }

    @Override
    public Result findPaperById(Integer paper_id) {
        try {
            Paper paper = paperMapper.findPaperById(paper_id);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化对象
            User user = (User) session.getAttribute("user");
            Map map = JSON.parseObject(JSON.toJSONString(paper), Map.class);
            map.put("user_name", user.getUser_name());
            map.put("publish_time", sdf.format(paper.getPublish_time()));
            rs = new Result("200", null, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public  Result findPersonalPaperMessage(Integer paper_id){
        try {
            Paper paper = paperMapper.findPaperById(paper_id);
            List<PaperTeamMap> paperTeamMaps = paperTeamMapper.selectPaperTeam(paper_id);
            Map<String,Object> map =new HashMap<>();
            List<Integer> periodicalIds = periodicalPaperMapper.findPeriodicalByPaperId(paper_id);

            map.put("paper",paper);
            map.put("paperTeamMaps",paperTeamMaps);
            map.put("periodicalIds",periodicalIds);
            rs = new Result("200",null,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
