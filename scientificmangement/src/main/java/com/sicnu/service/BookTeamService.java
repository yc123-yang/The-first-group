package com.sicnu.service;


import com.sicnu.pojo.BookTeam;
import com.sicnu.util.Result;


public interface BookTeamService {

    Result addBookTeamUser(Integer book_id,Integer user_id, Integer contribution);
    Result delBookTeamUser(Integer book_id,Integer user_id);
    Result selectBookTeam(Integer book_id);
    Result updateBookTeamUser(BookTeam bookTeam);

}
