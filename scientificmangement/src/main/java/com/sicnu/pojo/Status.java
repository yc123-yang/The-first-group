package com.sicnu.pojo;

import java.io.Serializable;

/**
 * 项目状态
 */
public class Status implements Serializable {
    private Integer status_id;
    private String status_name;

    public Status(Integer status_id, String status_name) {

        this.status_id = status_id;
        this.status_name = status_name;
    }

    public Integer getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Integer status_id) {
        this.status_id = status_id;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    @Override
    public String toString() {
        return "Status{" +
                "status_id=" + status_id +
                ", status_name='" + status_name + '\'' +
                '}';
    }
}
