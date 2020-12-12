package com.sicnu.service;

import com.sicnu.util.Result;


public interface OperationLogService {
    Result findAllOperationLogs(Integer pageSize, Integer pageNum);
    Result deleteAllOperationLogs();
}
