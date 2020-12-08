package com.sicnu.mapper;

import com.sicnu.pojo.Award;
import com.sicnu.pojo.AwardTeam;
import com.sicnu.pojo.teamMap.AwardTeamMap;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AwardTeamMapper {
    void addAwardTeamUser(Integer award_id, Integer user_id,Integer contribution);
    void delAwardTeamUser(Integer award_id,Integer user_id);
    List<AwardTeamMap> selectAwardTeam(Integer award_id);
    void updateAwardTeamUser(AwardTeam awardTeam);
}
