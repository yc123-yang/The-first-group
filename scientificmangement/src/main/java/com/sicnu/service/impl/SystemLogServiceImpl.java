package com.sicnu.service.impl;

import com.sicnu.mapper.OperationLogMapper;
import com.sicnu.mapper.SystemLogMapper;
import com.sicnu.pojo.LogsListResult;
import com.sicnu.pojo.OperationLog;
import com.sicnu.pojo.SystemLog;
import com.sicnu.service.SystemLogService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SystemLogServiceImpl implements SystemLogService {
    @Resource
    SystemLogMapper systemLogMapper;

    private Result rs = null;
    @Override
    public Result findAllSystemLogs(Integer pageSize, Integer pageNum) {
        LogsListResult logsListResult = null;
        try {
            Integer total = systemLogMapper.countAllSystemLogs();
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("pageSize", pageSize);
            map.put("pageNum", pageNum);
            List<SystemLog> systemLogList = systemLogMapper.findAllSystemLogs(map);
            logsListResult = new LogsListResult(total, systemLogList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs = new Result("200", "获取系统日志列表成功", logsListResult);
    }

    @Override
    public Result deleteAllSystemLogs() {
        try {
            systemLogMapper.deleteAllSystemLogs();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs = new Result("200", "清空系统日志成功", null);
    }
}
