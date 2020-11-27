package com.sicnu.pojo;

import java.util.List;

public class LogsListResult {
    private Integer total;
    private List<Slog> logsList;

    public LogsListResult(Integer total, List<Slog> logsList) {
        this.total = total;
        this.logsList = logsList;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Slog> getLogsList() {
        return logsList;
    }

    public void setLogsList(List<Slog> logsList) {
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
