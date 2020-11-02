package com.sicnu.service.impl;


import com.sicnu.dao.ProjectTeamDao;
import com.sicnu.service.ProjectTeamService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
@Service
public class ProjectTeamServiceImpl implements ProjectTeamService {
    @Resource
    ProjectTeamDao projectTeamDao;

    @Resource
    CheckTeamServiceImpl check_teamService;

    @Override
    public Result addProjectTeam(Integer project_id, Integer user_id, Date join_time) {
        Result rs =null;
        int number = projectTeamDao.selectNumberById(project_id);
        if (number<10){
            projectTeamDao.addProjectTeam(project_id, user_id, join_time);
            check_teamService.delCheckTeamUser(user_id);
            rs = new Result("0","添加成功",null);
        }else{
            rs =  new Result("1","小组已经满员，请您联系小组管理员",null);

        }
        return rs ;
    }

    @Override
    public Result delProjectTeam(Integer project_id) {
        Result rs =null;
        projectTeamDao.delProjectTeam(project_id);
        return rs = new Result("0","删除成功",null);
    }

    @Override
    public Result delTeamUser(Integer user_id) {
        Result rs =null;
        projectTeamDao.delTeamUser(user_id);
        return rs = new Result("0","删除成功",null);
    }
}
