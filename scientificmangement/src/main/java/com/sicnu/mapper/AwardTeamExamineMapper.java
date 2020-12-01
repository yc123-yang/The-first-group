package com.sicnu.mapper;

import com.sicnu.pojo.teamExamine.AwardTeamExamine;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AwardTeamExamineMapper {
    void addAwardExamineTeamUser(Integer award_id, Integer user_id, String user_role,Double contribution);
    void delAwardTeamExamineTeam(Integer award_id);
    List<AwardTeamExamine> selectAwardTeamExamineById(Integer award_id);
}
