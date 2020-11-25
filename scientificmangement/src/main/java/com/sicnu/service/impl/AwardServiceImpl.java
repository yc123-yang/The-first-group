package com.sicnu.service.impl;

import com.sicnu.mapper.AwardMapper;
import com.sicnu.mapper.CacheUserMapper;
import com.sicnu.pojo.Award;
import com.sicnu.pojo.CacheUser;
import com.sicnu.pojo.Patent;
import com.sicnu.service.AwardService;
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
public class AwardServiceImpl implements AwardService {
    @Resource
    AwardMapper awardMapper;

    private Result rs;

    @Resource
    CacheUserMapper cacheUserMapper;
    @Override
    public Result addAward(Award award) {
        try {
            Award award1 = awardMapper.selectAwardByNumber(award.getApproval_number());
            if (award1 != null) {

            } else {
                awardMapper.addAward(award);
                rs = new Result("200", "添加成功", null);
            }
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
