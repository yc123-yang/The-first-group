package com.sicnu.service;

import com.sicnu.util.Result;


import java.util.Date;

public interface CheckTeamService {
    Result checkProjectTeam(Integer project_id, Integer user_id, Date join_time);
    Result selectCheckUser(Integer project_id);
    void delCheckTeamUser(Integer user_id);

}
