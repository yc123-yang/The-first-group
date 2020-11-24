package com.sicnu.mapper;

import com.sicnu.pojo.ProjectTeam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProjectTeamMapper {
    void addTeamUser(Integer project_id, Integer role_id, Integer user_role);

    void delTeamUser(Integer user_id);

    List<Integer> selectProject_id(Integer user_id);
}
