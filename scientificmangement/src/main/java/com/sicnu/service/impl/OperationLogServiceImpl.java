package com.sicnu.service.impl;

import com.sicnu.mapper.LogsMapper;
import com.sicnu.mapper.OperationLogMapper;
import com.sicnu.pojo.LogsListResult;
import com.sicnu.pojo.OperationLog;
import com.sicnu.pojo.Slog;
import com.sicnu.service.OperationLogService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OperationLogServiceImpl implements OperationLogService {

    @Resource
    OperationLogMapper operationLogMapper;

    private Result rs = null;

    @Override
    public Result findAllOperationLogs(Integer pageSize, Integer pageNum) {
        LogsListResult logsListResult = null;
        try {
            Integer total = operationLogMapper.countAllOperationLogs();
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("pageSize", pageSize);
            map.put("pageNum", pageNum);
            List<OperationLog> operationLogList = operationLogMapper.findAllOperationLogs(map);
            logsListResult = new LogsListResult(total, operationLogList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs = new Result("200", "获取操作日志列表成功", logsListResult);
    }

    @Override
    public Result deleteAllOperationLogs() {
        try {
            operationLogMapper.deleteAllOperationLogs();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs = new Result("200", "清空操作日志成功", null);

    }
}
