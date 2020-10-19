package com.sicnu.service;

import com.sicnu.util.Result;
import jdk.internal.loader.Resource;

import java.util.Date;

public interface Check_TeamService {
    Result checkProjectTeam(Integer project_id, Integer user_id, Date join_time);
    Result selectCheckUser(Integer project_id);
    void delCheckTeamUser(Integer user_id);

}
