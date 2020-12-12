package com.sicnu.controller;

import com.sicnu.mapper.OperationLogMapper;
import com.sicnu.pojo.LogsListResult;
import com.sicnu.pojo.OperationLog;
import com.sicnu.service.impl.OperationLogServiceImpl;
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
public class OperationLogController {


    @Resource
    OperationLogServiceImpl operationLogService;

    private Result rs = null;

    @PostMapping("/logs/findAllOperationLogs")
    public Result findAllOperationLogs(Integer pageSize, Integer pageNum) {
        LogsListResult logsListResult = null;
        try {
            rs = operationLogService.findAllOperationLogs(pageSize, pageNum);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }
    @PostMapping("/logs/deleteAllOperationLogs")
    public Result deleteAllOperationLogs() {
        try {
            rs=operationLogService.deleteAllOperationLogs();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs ;

    }
}
