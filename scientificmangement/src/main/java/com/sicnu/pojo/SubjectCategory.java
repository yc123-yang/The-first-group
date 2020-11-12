package com.sicnu.pojo;

import java.io.Serializable;
import java.util.List;

public class SubjectCategory implements Serializable {
    private String sc_id;
    private String sc_name;
    private List<Subject> subjects;

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public String getSc_id() {
        return sc_id;
    }

    public void setSc_id(String sc_id) {
        this.sc_id = sc_id;
    }

    public String getSc_name() {
        return sc_name;
    }

    public void setSc_name(String sc_name) {
        this.sc_name = sc_name;
    }

    public SubjectCategory(String sc_id, String sc_name) {
        this.sc_id = sc_id;
        this.sc_name = sc_name;
    }

    @Override
    public String toString() {
        return "SubjectCategory{" +
                "sc_id='" + sc_id + '\'' +
                ", sc_name='" + sc_name + '\'' +
                ", subjects=" + subjects +
                '}';
    }
}
