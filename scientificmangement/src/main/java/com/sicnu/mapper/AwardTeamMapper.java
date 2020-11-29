package com.sicnu.mapper;

import com.sicnu.pojo.teamMap.AwardTeamMap;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AwardTeamMapper {
    void addAwardTeamUser(Integer award_id, Integer user_id, String user_role,Double contribution);
    void delAwardTeamUser(Integer user_id);
    List<AwardTeamMap> selectAwardTeam(Integer award_id);
}
