package com.sicnu.pojo;

import java.io.Serializable;
import java.util.Date;

public class Subject implements Serializable {

    private Integer subject_id;
    private String subject_name;
    private Date join_time;

    public Subject(Integer subject_id, String subject_name, Date join_time) {
        this.subject_id = subject_id;
        this.subject_name = subject_name;
        this.join_time = join_time;
    }

    public Integer getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Integer subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public Date getJoin_time() {
        return join_time;
    }

    public void setJoin_time(Date join_time) {
        this.join_time = join_time;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subject_id=" + subject_id +
                ", subject_name='" + subject_name + '\'' +
                ", join_time=" + join_time +
                '}';
    }
}
