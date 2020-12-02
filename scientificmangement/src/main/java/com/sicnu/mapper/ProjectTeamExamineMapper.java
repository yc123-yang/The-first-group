package com.sicnu.mapper;

import com.sicnu.pojo.teamExamine.ProjectTeamExamine;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProjectTeamExamineMapper {
    void addProjectTeamExamineUser(Integer pe_id,Integer user_id,String user_role,String team_role);
    void delProjectTeamExamineTeam(Integer pe_id);
    List<ProjectTeamExamine> selectProjectTeamExamineById(Integer pe_id);
}
