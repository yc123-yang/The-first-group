package com.sicnu.service.impl;


import com.sicnu.dao.Check_TeamDao;
import com.sicnu.dao.Project_TeamDao;
import com.sicnu.pojo.Check_Team;
import com.sicnu.service.Check_TeamService;
import com.sicnu.service.Project_TeamService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class Check_TeamServiceImpl implements Check_TeamService {
    @Resource
    Check_TeamDao check_teamDao;
    @Override
    public Result checkProjectTeam(Integer project_id, Integer user_id, Date join_time) {
        Result rs =null;
        check_teamDao.checkProjectTeam(project_id, user_id, join_time);
        return rs = new Result("0","您已经上传申请，请您等待回复",null);
    }

    @Override
    public Result selectCheckUser(Integer project_id) {
        List<Check_Team> check_teams = check_teamDao.selectCheckUser(project_id);
        Result rs =null;
        rs = new Result("0",null,check_teams);
        return rs;
    }

    @Override
    public void delCheckTeamUser(Integer user_id) {
        check_teamDao.delCheckTeamUser(user_id);
    }


}
