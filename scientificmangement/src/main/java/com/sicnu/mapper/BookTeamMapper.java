package com.sicnu.mapper;

import com.sicnu.pojo.BookTeam;
import com.sicnu.pojo.teamMap.BookTeamMap;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookTeamMapper {

    void addBookTeamUser(Integer book_id, Integer user_id, Integer contribution);
    void delBookTeamUser(Integer book_id,Integer user_id);
    List<BookTeamMap> selectBookTeam(Integer book_id);
    void updateBookTeamUser(BookTeam bookTeam);
}
