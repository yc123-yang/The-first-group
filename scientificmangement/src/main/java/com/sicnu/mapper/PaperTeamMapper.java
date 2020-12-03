package com.sicnu.mapper;

import com.sicnu.pojo.teamMap.PaperTeamMap;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PaperTeamMapper {

    void addPaperTeamUser(Integer paper_id,Integer user_id,Double contribution);
    void delPaperTeamUser(Integer user_id);
    List<PaperTeamMap> selectPaperTeam(Integer paper_id);
}
