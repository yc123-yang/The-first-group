package com.sicnu.pojo;

import java.io.Serializable;

public class Subject implements Serializable {

    private Integer subject_id;
    private String subject_name;
    private Integer sc_id;

    public Subject() {
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

    public Integer getSc_id() {
        return sc_id;
    }

    public void setSc_id(Integer sc_id) {
        this.sc_id = sc_id;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subject_id=" + subject_id +
                ", subject_name='" + subject_name + '\'' +
                ", sc_id=" + sc_id +
                '}';
    }
}
