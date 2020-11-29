package com.sicnu.service.impl;

import com.sicnu.mapper.AwardExamineMapper;
import com.sicnu.mapper.CacheUserMapper;
import com.sicnu.pojo.Award;
import com.sicnu.pojo.AwardExamine;
import com.sicnu.pojo.CacheUser;
import com.sicnu.service.AwardExamineService;
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
public class AwardExamineServiceImpl implements AwardExamineService {
    @Resource
    AwardExamineMapper awardExamineMapper;

    private Result rs;

    @Resource
    CacheUserMapper cacheUserMapper;

    @Override
    public Result addAwardExamine(AwardExamine awardExamine) {
        awardExamineMapper.addAwardExamine(awardExamine);
        rs = new Result("200","奖励已经录入系统审核,请您耐心等待",null);
        return rs;
    }

    @Override
    public Result selectAwardExamineByCondition(AwardExamine awardExamine, String award_time_start, String award_time_end, Integer pageSize, Integer pageNum, String apply_time_start, String apply_time_end) {
        List<Object> list = null;
        try {
            Map<String, Object> map = new HashMap<>();

            //获取登陆用户的缓存信息
            List<CacheUser> cacheUsers = cacheUserMapper.findAllCacheUser();
            //获取登录用户的id
            Integer uid = cacheUsers.get(0).getUser_id();
            map.put("achievement_name", awardExamine.getAchievement_name());
            map.put("leader_id", uid);
            map.put("award_name", awardExamine.getAward_name());
            map.put("issuing_authority", awardExamine.getIssuing_authority());
            map.put("approval_number", awardExamine.getApproval_number());
            map.put("aod_id", awardExamine.getAod_id());
            map.put("dr_id", awardExamine.getDr_id());
            map.put("ar_id", awardExamine.getAr_id());
            map.put("al_id", awardExamine.getAl_id());
            map.put("sc_id", awardExamine.getSc_id());
            map.put("subject_id", awardExamine.getSubject_id());
            map.put("sd_id", awardExamine.getSd_id());
            map.put("at_id", awardExamine.getAt_id());
            map.put("examine_status",awardExamine.getExamine_status());
            map.put("reviewer_id",awardExamine.getReviewer_id());
            map.put("pageSize", pageSize);
            map.put("pageNum", pageNum);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!award_time_start.equals("")) {
                map.put("award_time_start", sdf.parse(award_time_start));
            }
            if (!award_time_end.equals("")) {
                map.put("award_time_end", sdf.parse(award_time_end));
            }
            if (!apply_time_start.equals("")) {
                map.put("apply_time_start", sdf.parse(apply_time_start));
            }
            if (!apply_time_end.equals("")) {
                map.put("apply_time_end", sdf.parse(apply_time_end));
            }
            System.out.println(map);
            List<AwardExamine> awardExamines = awardExamineMapper.selectAwardExamineByCondition(map);
            Integer total = awardExamineMapper.selectTotalAwardExamine(map);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("total", total);
            list = new ArrayList<>();
            list.add(map1);
            list.add(awardExamines);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs = new Result("200", null, list);
    }

    @Override
    public Result delAwardExamineById(Integer ae_id) {
        awardExamineMapper.delAwardExamineById(ae_id);
        rs = new Result("200","删除成功",null);
        return rs;
    }
    public Result selectAllAwardExamineByCondition(AwardExamine awardExamine, String award_time_start, String award_time_end, Integer pageSize, Integer pageNum, String apply_time_start, String apply_time_end) {
        List<Object> list = null;
        try {
            Map<String, Object> map = new HashMap<>();


            map.put("achievement_name", awardExamine.getAchievement_name());
            map.put("award_name", awardExamine.getAward_name());
            map.put("issuing_authority", awardExamine.getIssuing_authority());
            map.put("approval_number", awardExamine.getApproval_number());
            map.put("aod_id", awardExamine.getAod_id());
            map.put("dr_id", awardExamine.getDr_id());
            map.put("ar_id", awardExamine.getAr_id());
            map.put("al_id", awardExamine.getAl_id());
            map.put("sc_id", awardExamine.getSc_id());
            map.put("subject_id", awardExamine.getSubject_id());
            map.put("sd_id", awardExamine.getSd_id());
            map.put("at_id", awardExamine.getAt_id());
            map.put("examine_status",awardExamine.getExamine_status());
            map.put("reviewer_id",awardExamine.getReviewer_id());
            map.put("pageSize", pageSize);
            map.put("pageNum", pageNum);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!award_time_start.equals("")) {
                map.put("award_time_start", sdf.parse(award_time_start));
            }
            if (!award_time_end.equals("")) {
                map.put("award_time_end", sdf.parse(award_time_end));
            }
            if (!apply_time_start.equals("")) {
                map.put("apply_time_start", sdf.parse(apply_time_start));
            }
            if (!apply_time_end.equals("")) {
                map.put("apply_time_end", sdf.parse(apply_time_end));
            }
            System.out.println(map);
            List<AwardExamine> awardExamines = awardExamineMapper.selectAwardExamineByCondition(map);
            Integer total = awardExamineMapper.selectTotalAwardExamine(map);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("total", total);
            list = new ArrayList<>();
            list.add(map1);
            list.add(awardExamines);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs = new Result("200", null, list);
    }


}
