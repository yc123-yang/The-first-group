package com.sicnu.service.impl;

import com.sicnu.mapper.LogsMapper;
import com.sicnu.pojo.LogsListResult;
import com.sicnu.pojo.Slog;
import com.sicnu.service.LogsService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LogsServiceImpl implements LogsService {

    @Resource
    LogsMapper logsDao;

    private Result rs = null;

    @Override
    public Result findAllLogs(Integer pageSize, Integer pageNum) {
        LogsListResult logsListResult = null;
        try {
            Integer total = logsDao.countAllLogs();
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("pageSize", pageSize);
            map.put("pageNum", pageNum);
            List<Slog> logList = logsDao.findAllLogs(map);
            logsListResult = new LogsListResult(total, logList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs = new Result("200", "获取安全日志列表成功", logsListResult);
    }

    @Override
    public Result deleteAllLogs() {
        try {
            logsDao.deleteAllLogs();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs = new Result("200", "清空日志成功", null);
    }
}
