package com.sicnu.pojo;

import java.io.Serializable;

public class Slog implements Serializable {
    private Integer log_id;
    private String log_date;
    private String log_level;
    private String log_msg;

    public Integer getLog_id() {
        return log_id;
    }

    public void setLog_id(Integer log_id) {
        this.log_id = log_id;
    }

    public String getLog_date() {
        return log_date;
    }

    public void setLog_date(String log_date) {
        this.log_date = log_date;
    }

    public String getLog_level() {
        return log_level;
    }

    public void setLog_level(String log_level) {
        this.log_level = log_level;
    }

    public String getLog_msg() {
        return log_msg;
    }

    public void setLog_msg(String log_msg) {
        this.log_msg = log_msg;
    }

    @Override
    public String toString() {
        return "Slog{" +
                "log_id=" + log_id +
                ", log_date='" + log_date + '\'' +
                ", log_level='" + log_level + '\'' +
                ", log_msg='" + log_msg + '\'' +
                '}';
    }
}
