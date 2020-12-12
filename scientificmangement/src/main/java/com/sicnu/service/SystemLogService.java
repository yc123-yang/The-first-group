package com.sicnu.service;

import com.sicnu.pojo.SystemLog;
import com.sicnu.util.Result;

import java.util.List;

public interface SystemLogService {
    Result findAllSystemLogs(Integer pageSize, Integer pageNum);
    Result deleteAllSystemLogs();
}
