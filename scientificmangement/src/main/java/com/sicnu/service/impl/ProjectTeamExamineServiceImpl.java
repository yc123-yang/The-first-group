package com.sicnu.service.impl;

import com.alibaba.fastjson.JSON;
import com.sicnu.mapper.ProjectTeamExamineMapper;
import com.sicnu.mapper.RoleMapper;
import com.sicnu.pojo.teamMap.ProjectTeamMap;
import com.sicnu.service.ProjectTeamExamineService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProjectTeamExamineServiceImpl implements ProjectTeamExamineService {
    @Resource
    ProjectTeamExamineMapper projectTeamExamineMapper;

    private Result rs;
    @Resource
    RoleMapper roleMapper;
    @Override
    public Result selectProjectTeamExamineUser(Integer pe_id) {
        try {
            List<ProjectTeamMap> projectTeamMaps = projectTeamExamineMapper.selectProjectTeamExamineUser(pe_id);
            List<Map> mapList = new ArrayList<>();
            for (int i = 0; i < projectTeamMaps.size(); i++) {
                Map map = JSON.parseObject(JSON.toJSONString(projectTeamMaps.get(i)),Map.class);
                map.put("role_name",roleMapper.selectRoleName(projectTeamMaps.get(i).getRole_id()));
                mapList.add(map);
            }
            rs = new Result("200",null,mapList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
