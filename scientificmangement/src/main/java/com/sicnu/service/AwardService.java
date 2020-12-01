package com.sicnu.service;

import com.sicnu.pojo.Award;
import com.sicnu.util.Result;

import java.text.ParseException;


public interface AwardService {

    Result addAward(Award award,String checkMessage,String message);

    Result selectAwardByCondition(Award award, String award_time_start, String award_time_end, Integer pageSize, Integer pageNum) throws ParseException;

    Result updateAward(Award award);

    Result delAward(Integer award_id);
    Result selectAllAwardByCondition(Award award, String award_time_start, String award_time_end, Integer pageSize, Integer pageNum) throws ParseException ;

    }
