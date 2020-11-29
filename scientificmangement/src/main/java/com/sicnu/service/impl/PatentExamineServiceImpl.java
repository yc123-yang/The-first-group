package com.sicnu.service.impl;

import com.sicnu.mapper.CacheUserMapper;
import com.sicnu.mapper.PatentExamineMapper;
import com.sicnu.pojo.CacheUser;
import com.sicnu.pojo.Patent;
import com.sicnu.pojo.PatentExamine;
import com.sicnu.service.PatentExamineService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PatentExamineServiceImpl implements PatentExamineService {

    @Resource
    PatentExamineMapper patentExamineMapper;

    private Result rs;

    @Resource
    CacheUserMapper cacheUserMapper;
    @Override
    public Result addPatentExamine(PatentExamine patentExamine) {
        try {
            patentExamineMapper.addPatentExamine(patentExamine);
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

            //获取登陆用户的缓存信息
            List<CacheUser> cacheUsers = cacheUserMapper.findAllCacheUser();
            //获取登录用户的id
            Integer uid = cacheUsers.get(0).getUser_id();

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
            Map<String, Object> map1 = new HashMap<>();
            map1.put("total", total);
            list = new ArrayList<>();
            list.add(map1);
            list.add(patentExamines);
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
            Map<String, Object> map1 = new HashMap<>();
            map1.put("total", total);
            list = new ArrayList<>();
            list.add(map1);
            list.add(patentExamines);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs = new Result("200", null, list);
    }
}
