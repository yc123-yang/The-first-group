package com.sicnu.service;

import com.sicnu.pojo.AwardExamine;
import com.sicnu.util.Result;


public interface AwardExamineService {

    Result addAwardExamine(AwardExamine awardExamine, Integer[] user_id,Integer[] contribution);
    Result selectAwardExamineByCondition(AwardExamine awardExamine, String award_time_start, String award_time_end, Integer pageSize, Integer pageNum,String apply_time_start,String apply_time_end);
    Result delAwardExamineById(Integer ae_id);
    Result findAwardExamineById(Integer ae_id);
}
