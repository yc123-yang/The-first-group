package com.sicnu.mapper;

import com.sicnu.pojo.teamExamine.BookTeamExamine;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookTeamExamineMapper {
    void addBookTeamExamineUser(Integer book_id, Integer user_id, String user_role,Double contribution);
    void delBookTeamExamineTeam(Integer book_id);
    List<BookTeamExamine> selectAwardTeamExamineById(Integer book_id);
}
