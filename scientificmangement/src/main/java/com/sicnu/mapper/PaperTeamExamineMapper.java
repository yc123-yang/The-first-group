package com.sicnu.mapper;

import com.sicnu.pojo.teamExamine.BookTeamExamine;
import com.sicnu.pojo.teamExamine.PaperTeamExamine;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PaperTeamExamineMapper {
    void addPaperTeamExamineUser(Integer pe_id, Integer user_id,Double contribution);
    void delPaperTeamExamineTeam(Integer pe_id);
    List<PaperTeamExamine> selectPaperTeamExamineById(Integer pe_id);
}
