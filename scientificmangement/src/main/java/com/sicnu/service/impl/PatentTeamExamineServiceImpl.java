package com.sicnu.service.impl;

import com.alibaba.fastjson.JSON;
import com.sicnu.mapper.PatentTeamExamineMapper;
import com.sicnu.mapper.RoleMapper;
import com.sicnu.pojo.teamMap.PatentTeamMap;
import com.sicnu.service.PatentTeamExamineService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PatentTeamExamineServiceImpl implements PatentTeamExamineService {

    @Resource
    PatentTeamExamineMapper patentTeamExamineMapper;

    private Result rs;
    @Resource
    RoleMapper roleMapper;
    @Override
    public Result selectPatentTeamExamineUser(Integer pe_id) {
        try {
            List<PatentTeamMap> patentTeamMaps = patentTeamExamineMapper.selectPatentTeamExamineUser(pe_id);
            List<Map> mapList = new ArrayList<>();
            for (int i = 0; i < patentTeamMaps.size(); i++) {
                Map map = JSON.parseObject(JSON.toJSONString(patentTeamMaps.get(i)),Map.class);
                map.put("role_name",roleMapper.selectRoleName(patentTeamMaps.get(i).getRole_id()));
                mapList.add(map);
            }
            rs = new Result("200",null,mapList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
