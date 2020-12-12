package com.sicnu.controller;

import com.sicnu.mapper.SystemLogMapper;
import com.sicnu.pojo.LogsListResult;
import com.sicnu.pojo.SystemLog;
import com.sicnu.service.impl.SystemLogServiceImpl;
import com.sicnu.util.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class SystemLogController {
    @Resource
    SystemLogServiceImpl systemLogService;

    private Result rs = null;

    @PostMapping("/logs/findAllSystemLogs")

    public Result findAllSystemLogs(Integer pageSize, Integer pageNum) {
        LogsListResult logsListResult = null;
        try {
            rs = systemLogService.findAllSystemLogs(pageSize, pageNum);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs ;
    }

    @PostMapping("/logs/deleteAllSystemLogs")

    public Result deleteAllSystemLogs() {
        try {
            rs = systemLogService.deleteAllSystemLogs();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs ;
    }
}
