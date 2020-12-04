package com.sicnu.service;

import com.sicnu.pojo.Patent;
import com.sicnu.util.Result;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface PatentService {

    Result addPatent(Patent patent,String checkMessage,String message);

    Result selectPatentByCondition(Patent patent, String application_time_start, String application_time_end, String public_time_start, String public_time_end, String authorization_time_start, String authorization_time_end, Integer pageSize, Integer pageNum) throws ParseException;

    Result updatePatent(Patent patent);

    Result delPatentById(Integer patent_id);
    Result selectAllPatentByCondition(Patent patent, String application_time_start, String application_time_end, String public_time_start, String public_time_end, String authorization_time_start, String authorization_time_end, Integer pageSize, Integer pageNum) throws ParseException;
    Result findPatentById(Integer patent_id);
}
