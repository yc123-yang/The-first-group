package com.sicnu.service.impl;

import com.alibaba.fastjson.JSON;
import com.sicnu.mapper.AwardExamineMapper;
import com.sicnu.mapper.AwardTeamExamineMapper;
import com.sicnu.mapper.RoleMapper;
import com.sicnu.pojo.AwardTeam;
import com.sicnu.pojo.PaperExamine;
import com.sicnu.pojo.teamMap.AwardTeamMap;
import com.sicnu.service.AwardTeamExamineService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AwardTeamExamineServiceImpl implements AwardTeamExamineService {
    @Resource
    AwardTeamExamineMapper awardTeamExamineMapper;
    private Result rs;
    @Resource
    RoleMapper roleMapper;
    @Override
    public Result selectAwardTeamExamineUser(Integer ae_id) {
        try {
            List<AwardTeamMap> awardTeamMaps = awardTeamExamineMapper.selectAwardTeamExamineUser(ae_id);
            List<Map> mapList = new ArrayList<>();
            for (int i = 0; i < awardTeamMaps.size(); i++) {
                Map map = JSON.parseObject(JSON.toJSONString(awardTeamMaps.get(i)),Map.class);
                map.put("role_name",roleMapper.selectRoleName(awardTeamMaps.get(i).getRole_id()));
                mapList.add(map);
            }
            rs = new Result("200",null,mapList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
