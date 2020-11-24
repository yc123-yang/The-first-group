package com.sicnu.service;

import com.sicnu.pojo.Award;
import com.sicnu.util.Result;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface AwardService {

    Result addAward(Award award);

    Result selectAwardByCondition(Award award, String award_time_start, String award_time_end, Integer pageSize, Integer pageNum) throws ParseException;

    Result updateAward(Award award);

    Result delAward(Integer award_id);
}
