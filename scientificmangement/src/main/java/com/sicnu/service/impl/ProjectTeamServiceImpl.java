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
    public Result addProjectTeamUser(Integer project_id, String user_name, String user_role, Integer role_id, Integer department_id) {
        try {

            int user_id = userMapper.findByUserName(user_name);
            User user = userMapper.findUserById(user_id);
            if (!user.getRole_id().equals(role_id) && !user.getDepartment_id().equals(department_id)) {
                rs = new Result("400", "成员信息有误，无法添加", null);
            } else {
                projectTeamMapper.addProjectTeamUser(project_id, user_id, role_id);
                rs = new Result("200", "成员添加成功", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delProjectTeamUser(Integer user_id) {
        try {
            projectTeamMapper.delProjectTeamUser(user_id);
            rs = new Result("200", "删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result selectProjectTeam(Integer project_id) {
        List<ProjectTeamMap> projectTeamMaps = projectTeamMapper.selectProjectTeam(project_id);
        rs = new Result("200",null,projectTeamMaps);
        return rs;
    }
}
