package com.sicnu.service;

import com.sicnu.pojo.Paper;
import com.sicnu.pojo.PaperExamine;
import com.sicnu.util.Result;

import java.util.List;
import java.util.Map;


public interface PaperExamineService {
    Result addPaperExamine(PaperExamine paperExamine,Integer[] user_id,Double[] contribution);
    Result selectPaperExamineByCondition(PaperExamine paperExamine, String publish_time_start, String publish_time_end, Integer pageNum, Integer pageSize, String apply_time_start, String apply_time_end);
    Result delPaperExamineById(Integer pe_id);
    Result findPaperExamineById(Integer pe_id);

}
