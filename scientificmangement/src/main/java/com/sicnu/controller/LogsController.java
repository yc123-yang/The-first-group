package com.sicnu.controller;

import com.sicnu.service.impl.LogsServiceImpl;
import com.sicnu.util.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@CrossOrigin
public class LogsController {
    @Resource
    LogsServiceImpl logsService;

    @PostMapping("/logs/findAllLogs")
    public Result findAllLogs(Integer pageSize, Integer pageNum) {
        return logsService.findAllLogs(pageSize, pageNum);
    }

    @PostMapping("/logs/deleteAllLogs")
    public Result deleteAllLogs() {
        return logsService.deleteAllLogs();
    }
}
