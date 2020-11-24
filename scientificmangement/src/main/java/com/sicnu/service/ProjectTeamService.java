package com.sicnu.service;

import com.sicnu.pojo.ProjectTeam;
import org.apache.ibatis.annotations.Mapper;
import com.sicnu.util.Result;
import org.springframework.stereotype.Repository;


public interface ProjectTeamService {
    Result addTeamUser(Integer project_id, String user_name, String user_role, Integer role_id, Integer department_id);

    Result delTeamUser(Integer user_id);

}
