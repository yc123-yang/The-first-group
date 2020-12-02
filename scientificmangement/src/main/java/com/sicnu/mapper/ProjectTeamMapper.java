package com.sicnu.mapper;

import com.sicnu.pojo.teamMap.ProjectTeamMap;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProjectTeamMapper {
    void addProjectTeamUser(Integer project_id, Integer user_id, String team_role, String user_role);

    void delProjectTeamUser(Integer user_id);

    List<Integer> selectProject_id(Integer user_id);
    List<ProjectTeamMap> selectProjectTeam(Integer project_id);
}
