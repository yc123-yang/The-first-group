package com.sicnu.service.impl;

import com.alibaba.fastjson.JSON;
import com.sicnu.mapper.CacheUserMapper;
import com.sicnu.mapper.PatentExamineMapper;
import com.sicnu.mapper.PatentTeamExamineMapper;
import com.sicnu.mapper.UserMapper;
import com.sicnu.pojo.*;
import com.sicnu.pojo.teamMap.PaperTeamMap;
import com.sicnu.pojo.teamMap.PatentTeamMap;
import com.sicnu.service.PatentExamineService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PatentExamineServiceImpl implements PatentExamineService {

    @Resource
    PatentExamineMapper patentExamineMapper;

    private Result rs;
    @Resource
    PatentTeamExamineMapper patentTeamExamineMapper;
    @Resource
    CacheUserMapper cacheUserMapper;

    @Resource
    private HttpSession session;
    @Resource
    UserMapper userMapper;
    @Override
    public Result addPatentExamine(PatentExamine patentExamine,Integer[] user_id,Integer[] contribution) {
        try {
            patentExamine.setExamine_status("未审核");
            patentExamine.setApply_time(new Date());
            patentExamine.setReviewer_id(1);
            patentExamineMapper.addPatentExamine(patentExamine);
            Integer patent_id = patentExamineMapper.selectPatentExamineId(patentExamine.getLeader_id(),patentExamine.getPatent_name());
            for (int i = 0; i < user_id.length; i++) {
                patentTeamExamineMapper.addPatentTeamExamineUser(patent_id,user_id[i],contribution[i]);
            }
            rs =new Result("200","您的专利已经上传审核，请您耐心等待审核结果",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result selectPatentExamineByCondition(PatentExamine patentExamine, String application_time_start, String application_time_end, String public_time_start, String public_time_end, String authorization_time_start, String authorization_time_end, Integer pageSize, Integer pageNum, String apply_time_start, String apply_time_end) {
        List<Object> list = null;
        try {
            Map<String, Object> map = new HashMap<>();

            User user = (User) session.getAttribute("user");
            //获取登陆用户的缓存信息
//            List<CacheUser> cacheUsers = cacheUserMapper.findAllCacheUser();
            //获取登录用户的id
            Integer uid = user.getUser_id();

            map.put("patent_name", patentExamine.getPatent_name());
            map.put("leader_id", uid);
            map.put("pt_id", patentExamine.getPt_id());
            map.put("pr_id", patentExamine.getPr_id());
            map.put("ps_id", patentExamine.getPs_id());
            map.put("aod_id", patentExamine.getAod_id());
            map.put("application_number", patentExamine.getApplication_number());
            map.put("public_number", patentExamine.getPublic_number());
            map.put("authorization_number", patentExamine.getApplication_number());
            map.put("examine_status",patentExamine.getExamine_status());
            map.put("reviewer_id",patentExamine.getReviewer_id());
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
            if (!apply_time_start.equals("")) {
                map.put("apply_time_start", sdf.parse(apply_time_start));
            }
            if (!apply_time_end.equals("")) {
                map.put("apply_time_end", sdf.parse(apply_time_end));
            }
            System.out.println(map);
            Integer total = patentExamineMapper.selectTotalPatentExamine(map);
            List<PatentExamine> patentExamines = patentExamineMapper.selectPatentExamineByCondition(map);
            List<Map> mapList = new ArrayList<>();
            for (int i = 0; i < patentExamines.size(); i++) {
                Map temp = JSON.parseObject(JSON.toJSONString(patentExamines.get(i)),Map.class);
                temp.put("user_name",userMapper.findUserById(patentExamines.get(i).getLeader_id()).getUser_name());
                temp.put("application_time",sdf.format(patentExamines.get(i).getApplication_time()));
                temp.put("authorization_time",sdf.format(patentExamines.get(i).getAuthorization_time()));
                temp.put("public_time",sdf.format(patentExamines.get(i).getPublic_time()));
                temp.put("apply_time",sdf.format(patentExamines.get(i).getApply_time()));

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
    public Result delPatentExamineById(Integer pe_id) {
        try {
            patentExamineMapper.delPatentExamineById(pe_id);
            rs =new Result("200","删除成功",null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findPatentExamineById(Integer pe_id) {
        try {
            PatentExamine patentExamine = patentExamineMapper.findPatentExamineById(pe_id);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化对象
            User user = (User) session.getAttribute("user");
            Map map = JSON.parseObject(JSON.toJSONString(patentExamine), Map.class);
            map.put("user_name", user.getUser_name());
            map.put("application_time", sdf.format(patentExamine.getApplication_time()));
            map.put("authorization_time", sdf.format(patentExamine.getAuthorization_time()));
            map.put("public_time", sdf.format(patentExamine.getPublic_time()));
            map.put("apply_time", sdf.format(patentExamine.getApply_time()));
            rs = new Result("200", null, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public Result selectAllPatentExamineByCondition(PatentExamine patentExamine, String application_time_start, String application_time_end, String public_time_start, String public_time_end, String authorization_time_start, String authorization_time_end, Integer pageSize, Integer pageNum, String apply_time_start, String apply_time_end) {
        List<Object> list = null;
        try {
            Map<String, Object> map = new HashMap<>();

            map.put("patent_name", patentExamine.getPatent_name());
            map.put("pt_id", patentExamine.getPt_id());
            map.put("pr_id", patentExamine.getPr_id());
            map.put("ps_id", patentExamine.getPs_id());
            map.put("aod_id", patentExamine.getAod_id());
            map.put("application_number", patentExamine.getApplication_number());
            map.put("public_number", patentExamine.getPublic_number());
            map.put("authorization_number", patentExamine.getApplication_number());
            map.put("examine_status",patentExamine.getExamine_status());
            map.put("reviewer_id",patentExamine.getReviewer_id());
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
            if (!apply_time_start.equals("")) {
                map.put("apply_time_start", sdf.parse(apply_time_start));
            }
            if (!apply_time_end.equals("")) {
                map.put("apply_time_end", sdf.parse(apply_time_end));
            }
            System.out.println(map);
            Integer total = patentExamineMapper.selectTotalPatentExamine(map);
            List<PatentExamine> patentExamines = patentExamineMapper.selectPatentExamineByCondition(map);
            List<Map> mapList = new ArrayList<>();
            for (int i = 0; i < patentExamines.size(); i++) {
                Map temp = JSON.parseObject(JSON.toJSONString(patentExamines.get(i)),Map.class);
                temp.put("user_name",userMapper.findUserById(patentExamines.get(i).getLeader_id()).getUser_name());
                temp.put("application_time",sdf.format(patentExamines.get(i).getApplication_time()));
                temp.put("authorization_time",sdf.format(patentExamines.get(i).getAuthorization_time()));
                temp.put("public_time",sdf.format(patentExamines.get(i).getPublic_time()));
                temp.put("apply_time",sdf.format(patentExamines.get(i).getApply_time()));

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

    public Result findPersonalPatentExamineMessage(Integer pe_id){
        try {
            List<PatentTeamMap> patentTeamMaps = patentTeamExamineMapper.selectPatentTeamExamineUser(pe_id);
            PatentExamine patentExamine = patentExamineMapper.findPatentExamineById(pe_id);
            Map<String,Object> map = new HashMap<>();
            map.put("patentTeamMaps",patentTeamMaps);
            map.put("patentExamine",patentExamine);
            rs = new Result("200",null,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
