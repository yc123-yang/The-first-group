package com.sicnu.mapper;

import com.sicnu.pojo.teamExamine.PatentTeamExamine;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PatentTeamExamineMapper {
    void addPatentTeamExamineUser(Integer patent_id, Integer user_id, String user_role,Double contribution);
    void delPatentTeamExamineTeam(Integer patent_id);
    List<PatentTeamExamine> selectPatentTeamExamineById(Integer patent_id);
}
