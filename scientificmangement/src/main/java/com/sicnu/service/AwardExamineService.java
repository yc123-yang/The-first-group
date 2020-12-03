package com.sicnu.service;

import com.sicnu.pojo.Award;
import com.sicnu.pojo.AwardExamine;
import com.sicnu.util.Result;
import com.sun.mail.imap.protocol.INTERNALDATE;

import java.util.List;
import java.util.Map;

public interface AwardExamineService {

    Result addAwardExamine(AwardExamine awardExamine, Integer[] user_id,Double[] contribution);
    Result selectAwardExamineByCondition(AwardExamine awardExamine, String award_time_start, String award_time_end, Integer pageSize, Integer pageNum,String apply_time_start,String apply_time_end);
    Result delAwardExamineById(Integer ae_id);
}
