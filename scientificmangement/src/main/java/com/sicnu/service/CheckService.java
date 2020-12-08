package com.sicnu.service;

import com.sicnu.util.Result;

public interface CheckService {
    void addAllFinalCheck();
    Result selectAllCheckByCondition(Integer user_id,Integer department_id,String check_time);

}
