package com.sicnu.service;

import com.sicnu.pojo.Patent;
import com.sicnu.pojo.PatentExamine;
import com.sicnu.util.Result;

import java.util.List;
import java.util.Map;

public interface PatentExamineService {
    Result addPatentExamine(PatentExamine patentExamine);
    Result selectPatentExamineByCondition(PatentExamine patentExamine, String application_time_start, String application_time_end, String public_time_start, String public_time_end, String authorization_time_start, String authorization_time_end, Integer pageSize, Integer pageNum,String apply_time_start,String apply_time_end);
    Result delPatentExamineById(Integer pe_id);
}
