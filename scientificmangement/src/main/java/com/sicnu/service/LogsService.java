package com.sicnu.service;

import com.sicnu.util.Result;

public interface LogsService {
    Result findAllLogs(Integer pageSize, Integer pageNum);
    Result deleteAllLogs();
}
