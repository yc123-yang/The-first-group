package com.sicnu.service;

import com.sicnu.pojo.teamMap.BookTeamMap;
import com.sicnu.util.Result;

import java.util.List;

public interface BookTeamService {

    Result addBookTeamUser(Integer book_id,Integer user_id, Double contribution);
    Result delBookTeamUser(Integer user_id);
    Result selectBookTeam(Integer book_id);
}
