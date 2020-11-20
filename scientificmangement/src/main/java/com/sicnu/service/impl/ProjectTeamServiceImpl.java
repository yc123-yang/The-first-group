package com.sicnu.service.impl;

import com.sicnu.mapper.ProjectTeamMapper;
import com.sicnu.mapper.UserMapper;
import com.sicnu.pojo.User;
import com.sicnu.service.ProjectTeamService;

import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 项目组
 */
@Service
public class ProjectTeamServiceImpl implements ProjectTeamService {

    @Resource
    ProjectTeamMapper projectTeamMapper;
    @Resource
    UserMapper userMapper;
    @Override
    public Result addTeamUser(Integer project_id, String user_name, String user_role, Integer role_id, Integer department_id) {
        Result rs = null;
        int user_id = userMapper.findByUserName(user_name);
        User user = userMapper.findUserById(user_id);
        if (!user.getRole_id().equals(role_id)&&!user.getDepartment_id().equals(department_id)){
            rs = new Result("1","成员信息有误，无法添加",null);
        }else{
            projectTeamMapper.addTeamUser(project_id,user_id,role_id);
            rs = new Result("1","成员添加成功",null);
        }
        return rs;
    }

    @Override
    public Result delTeamUser(Integer user_id) {
        Result rs = null;
        projectTeamMapper.delTeamUser(user_id);
        rs = new Result("0","删除成功",null);
        return rs;
    }
}
