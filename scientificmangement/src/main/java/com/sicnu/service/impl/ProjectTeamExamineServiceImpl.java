package com.sicnu.service.impl;

import com.sicnu.mapper.ProjectTeamExamineMapper;
import com.sicnu.pojo.teamMap.ProjectTeamMap;
import com.sicnu.service.ProjectTeamExamineService;
import com.sicnu.util.Result;

import javax.annotation.Resource;
import java.util.List;

public class ProjectTeamExamineServiceImpl implements ProjectTeamExamineService {
    @Resource
    ProjectTeamExamineMapper projectTeamExamineMapper;

    private Result rs;

    @Override
    public Result selectProjectTeamExamineUser(Integer pe_id) {
        List<ProjectTeamMap> projectTeamMaps = projectTeamExamineMapper.selectProjectTeamExamineUser(pe_id);
        rs = new Result("200",null,projectTeamMaps);
        return rs;
    }
}
