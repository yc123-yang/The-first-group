package com.sicnu.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 一级学科
 */
public class Subject implements Serializable {

    private String subject_id;
    private String subject_name;
    private String sc_id;

    public Subject() {
    }


    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getSc_id() {
        return sc_id;
    }

    public void setSc_id(String sc_id) {
        this.sc_id = sc_id;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subject_id='" + subject_id + '\'' +
                ", subject_name='" + subject_name + '\'' +
                ", sc_id='" + sc_id + '\'' +
                '}';
    }
}
