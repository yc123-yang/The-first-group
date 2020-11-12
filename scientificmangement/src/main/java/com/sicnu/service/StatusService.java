package com.sicnu.service;

import com.sicnu.pojo.Status;
import com.sicnu.util.Result;

import java.util.List;

public interface StatusService {
    Result addStatus(String status_name);
    Result delStatus(Integer status_id);
    Result findAllStatus();
}
