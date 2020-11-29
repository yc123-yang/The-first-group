package com.sicnu.mapper;

import com.sicnu.pojo.teamMap.ProjectTeamMap;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProjectTeamMapper {
    void addProjectTeamUser(Integer project_id, Integer role_id, Integer user_role);

    void delProjectTeamUser(Integer user_id);

    List<Integer> selectProject_id(Integer user_id);
    List<ProjectTeamMap> selectProjectTeam(Integer project_id);
}
