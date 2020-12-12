package com.sicnu.pojo;

import java.util.List;

public class LogsListResult {
    private Integer total;
    private Object logsList;

    public LogsListResult(Integer total, Object logsList) {
        this.total = total;
        this.logsList = logsList;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Object getLogsList() {
        return logsList;
    }

    public void setLogsList(Object logsList) {
        this.logsList = logsList;
    }

    @Override
    public String toString() {
        return "LogsListResult{" +
                "total=" + total +
                ", logsList=" + logsList +
                '}';
    }
}
