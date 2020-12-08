package com.sicnu.mapper;

import com.sicnu.pojo.PatentTeam;
import com.sicnu.pojo.teamMap.BookTeamMap;
import com.sicnu.pojo.teamMap.PatentTeamMap;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PatentTeamMapper {

    void addPatentTeamUser(Integer patent_id, Integer user_id, Integer contribution);
    void delPatentTeamUser(Integer patent_id,Integer user_id);
    List<PatentTeamMap> selectPatentTeam(Integer patent_id);
    void updatePatentTeamUser(PatentTeam patentTeam);
}
