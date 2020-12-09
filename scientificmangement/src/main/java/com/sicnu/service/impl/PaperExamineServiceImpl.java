package com.sicnu.service.impl;

import com.sicnu.mapper.CacheUserMapper;
import com.sicnu.mapper.PaperExamineMapper;
import com.sicnu.mapper.PaperTeamExamineMapper;
import com.sicnu.mapper.PeriodicalPaperExamineMapper;
import com.sicnu.pojo.*;
import com.sicnu.pojo.teamMap.BookTeamMap;
import com.sicnu.pojo.teamMap.PaperTeamMap;
import com.sicnu.service.PaperExamineService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PaperExamineServiceImpl implements PaperExamineService {

    @Resource
    PaperExamineMapper paperExamineMapper;

    private Result rs;

    @Resource
    CacheUserMapper cacheUserMapper;

    @Resource
    PaperTeamExamineMapper paperTeamExamineMapper;

    @Resource
    PeriodicalPaperExamineMapper periodicalPaperExamineMapper;

    @Resource
    private HttpSession session;

    @Override
    public Result addPaperExamine(PaperExamine paperExamine,Integer[] user_id,Integer[] contribution,Integer[] periodicalIds) {
        try {
            paperExamine.setExamine_status("未审核");
            paperExamine.setApply_time(new Date());
            paperExamine.setReviewer_id(1);
            paperExamineMapper.addPaperExamine(paperExamine);
            Integer paper_id = paperExamineMapper.selectPaperExamineId(paperExamine.getLeader_id(),paperExamine.getPaper_name());
            for (int i = 0; i < user_id.length; i++) {
                paperTeamExamineMapper.addPaperTeamExamineUser(paper_id,user_id[i],contribution[i]);
            }
            for (int i = 0; i < periodicalIds.length; i++) {
                periodicalPaperExamineMapper.addPeriodicalPaperExamine(paper_id,periodicalIds[i]);
            }
            rs = new Result("200","您的论文已经上传审核，请您等待审核结果",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result selectPaperExamineByCondition(PaperExamine paperExamine, String publish_time_start, String publish_time_end, Integer pageNum, Integer pageSize, String apply_time_start, String apply_time_end) {
        List<Object> list = null;
        try {
            Map<String, Object> map = new HashMap<>();


            User user = (User) session.getAttribute("user");
            //获取登陆用户的缓存信息
            List<CacheUser> cacheUsers = cacheUserMapper.findAllCacheUser();
            //获取登录用户的id
            Integer uid = user.getUser_id();

            map.put("paper_name", paperExamine.getPaper_name());
            map.put("pt_id", paperExamine.getPt_id());
            map.put("leader_id", uid);
            map.put("periodical_id", paperExamine.getPeriodical_id());
            map.put("include_number", paperExamine.getInclude_number());
            map.put("sc_id", paperExamine.getSc_id());
            map.put("subject_id", paperExamine.getSubject_id());
            map.put("aod_id", paperExamine.getAod_id());
            map.put("sd_id", paperExamine.getSd_id());
            map.put("remark", paperExamine.getRemark());
            map.put("examine_status",paperExamine.getExamine_status());
            map.put("reviewer_id",paperExamine.getReviewer_id());
            map.put("pageNum", pageNum);
            map.put("pageSize", pageSize);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!publish_time_start.equals("")) {
                map.put("publish_time_start", sdf.parse(publish_time_start));
            }
            if (!publish_time_end.equals("")) {
                map.put("publish_time_end", sdf.parse(publish_time_end));
            }
            if (!apply_time_start.equals("")) {
                map.put("apply_time_start", sdf.parse(apply_time_start));
            }
            if (!apply_time_end.equals("")) {
                map.put("apply_time_end", sdf.parse(apply_time_end));
            }
            System.out.println("map:"+ map);
            List<PaperExamine> paperExamines = paperExamineMapper.selectPaperExamineByCondition(map);

            Integer total = paperExamineMapper.selectTotalPaperExamine(map);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("total", total);
            list = new ArrayList<>();
            list.add(map1);
            list.add(paperExamines);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs = new Result("200", null, list);
    }

    @Override
    public Result delPaperExamineById(Integer pe_id) {
        paperExamineMapper.delPaperExamineById(pe_id);
        return rs;
    }

    @Override
    public Result findPaperExamineById(Integer pe_id) {
        try {
            PaperExamine paperExamine = paperExamineMapper.findPaperExamineById(pe_id);
            rs = new Result("200",null,paperExamine);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public Result selectAllPaperExamineByCondition(PaperExamine paperExamine, String publish_time_start, String publish_time_end, Integer pageNum, Integer pageSize, String apply_time_start, String apply_time_end) {
        List<Object> list = null;
        try {
            Map<String, Object> map = new HashMap<>();



            map.put("paper_name", paperExamine.getPaper_name());
            map.put("pt_id", paperExamine.getPt_id());
            map.put("periodical_id", paperExamine.getPeriodical_id());
            map.put("publish_time", paperExamine.getPublish_time());
            map.put("include_number", paperExamine.getInclude_number());
            map.put("sc_id", paperExamine.getSc_id());
            map.put("subject_id", paperExamine.getSubject_id());
            map.put("aod_id", paperExamine.getAod_id());
            map.put("sd_id", paperExamine.getSd_id());
            map.put("remark", paperExamine.getRemark());
            map.put("examine_status",paperExamine.getExamine_status());
            map.put("reviewer_id",paperExamine.getReviewer_id());
            map.put("pageNum", pageNum);
            map.put("pageSize", pageSize);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!publish_time_start.equals("")) {
                map.put("publish_time_start", sdf.parse(publish_time_start));
            }
            if (!publish_time_end.equals("")) {
                map.put("publish_time_end", sdf.parse(publish_time_end));
            }
            if (!apply_time_start.equals("")) {
                map.put("apply_time_start", sdf.parse(apply_time_start));
            }
            if (!apply_time_end.equals("")) {
                map.put("apply_time_end", sdf.parse(apply_time_end));
            }
            System.out.println("map:"+ map);
            List<PaperExamine> paperExamines = paperExamineMapper.selectPaperExamineByCondition(map);

            Integer total = paperExamineMapper.selectTotalPaperExamine(map);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("total", total);
            list = new ArrayList<>();
            list.add(map1);
            list.add(paperExamines);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs = new Result("200", null, list);
    }


    public Result findPersonalPaperExamineMessage(Integer pe_id){
        try {
            List<PaperTeamMap> paperTeamMaps = paperTeamExamineMapper.selectPaperTeamExamineUser(pe_id);
            PaperExamine paperExamine = paperExamineMapper.findPaperExamineById(pe_id);
            List<Integer> periodicalIds = periodicalPaperExamineMapper.findPeriodicalExamineByPaperId(pe_id);
            Map<String,Object> map = new HashMap<>();
            map.put("paperExamine",paperExamine);
            map.put("paperTeamMaps",paperTeamMaps);
            map.put("periodicalIds",periodicalIds);
            rs = new Result("200",null,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
