package com.sicnu.service.impl;


import com.sicnu.dao.Project_TeamDao;
import com.sicnu.service.Project_TeamService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
@Service
public class Project_TeamServiceImpl implements Project_TeamService {
    @Resource
    Project_TeamDao projectTeamDao;
    @Override
    public Result addProjectTeam(Integer project_id, Integer user_id, Date join_time) {
        Result rs =null;
        projectTeamDao.addProjectTeam(project_id, user_id, join_time);
        return rs = new Result("0","添加成功",null);
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
