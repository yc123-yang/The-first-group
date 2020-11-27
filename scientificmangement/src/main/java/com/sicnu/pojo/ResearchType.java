package com.sicnu.pojo;

public class ResearchType {
    private Integer rt_id;
    private String rt_name;

    public Integer getRt_id() {
        return rt_id;
    }

    public void setRt_id(Integer rt_id) {
        this.rt_id = rt_id;
    }

    public String getRt_name() {
        return rt_name;
    }

    public void setRt_name(String rt_name) {
        this.rt_name = rt_name;
    }

    @Override
    public String toString() {
        return "ResearchType{" +
                "rt_id=" + rt_id +
                ", rt_name='" + rt_name + '\'' +
                '}';
    }
}
