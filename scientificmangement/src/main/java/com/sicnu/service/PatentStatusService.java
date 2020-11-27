package com.sicnu.service;

import com.sicnu.pojo.PatentStatus;
import com.sicnu.util.Result;

import java.util.List;

public interface PatentStatusService {
    Result addPatentStatus(String ps_name);
    Result delPatentStatus(Integer ps_id);
    Result findAllPatentStatus();
    Result updatePatentRange(PatentStatus patentStatus);
}
