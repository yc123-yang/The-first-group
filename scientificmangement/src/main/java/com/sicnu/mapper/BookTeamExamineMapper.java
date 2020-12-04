package com.sicnu.mapper;

import com.sicnu.pojo.teamExamine.BookTeamExamine;
import com.sicnu.pojo.teamMap.BookTeamMap;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookTeamExamineMapper {
    void addBookTeamExamineUser(Integer be_id, Integer user_id,Double contribution);
    void delBookTeamExamineTeam(Integer be_id);
    List<BookTeamExamine> selectAwardTeamExamineById(Integer be_id);
    List<BookTeamMap> selectBookTeamExamineUser(Integer be_id);
}
