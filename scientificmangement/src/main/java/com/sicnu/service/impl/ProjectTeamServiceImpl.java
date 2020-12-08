package com.sicnu.service.impl;

import com.sicnu.mapper.ProjectTeamMapper;
import com.sicnu.mapper.UserMapper;
import com.sicnu.pojo.teamMap.ProjectTeamMap;
import com.sicnu.pojo.User;
import com.sicnu.service.ProjectTeamService;

import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 项目组
 */
@Service
public class ProjectTeamServiceImpl implements ProjectTeamService {

    @Resource
    ProjectTeamMapper projectTeamMapper;

    @Resource
    UserMapper userMapper;

    private Result rs = null;
    @Override
    public Result addProjectTeamUser(Integer project_id, Integer user_id, String team_role) {
        try {
                projectTeamMapper.addProjectTeamUser(project_id, user_id,team_role);
                rs = new Result("200", "成员添加成功", null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delProjectTeamUser(Integer project_id,Integer user_id) {
        try {
            projectTeamMapper.delProjectTeamUser(project_id,user_id);
            rs = new Result("200", "删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result selectProjectTeam(Integer project_id) {
        try {
            List<ProjectTeamMap> projectTeamMaps = projectTeamMapper.selectProjectTeam(project_id);
            rs = new Result("200",null,projectTeamMaps);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result updateProjectTeamUser(String team_role) {
        try {
            projectTeamMapper.updateProjectTeamUser(team_role);
            rs = new Result("200","更改成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
