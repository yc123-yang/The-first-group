package com.sicnu.pojo;

import java.io.Serializable;

/**
 * 结题
 */
public class ConclusionType implements Serializable {
    private Integer ct_id;
    private String ct_name;

    public ConclusionType(Integer ct_id, String ct_name) {

        this.ct_id = ct_id;
        this.ct_name = ct_name;
    }


    public Integer getCt_id() {
        return ct_id;
    }

    public void setCt_id(Integer ct_id) {
        this.ct_id = ct_id;
    }

    public String getCt_name() {
        return ct_name;
    }

    public void setCt_name(String ct_name) {
        this.ct_name = ct_name;
    }

    @Override
    public String toString() {
        return "ConclusionType{" +
                "ct_id=" + ct_id +
                ", ct_name='" + ct_name + '\'' +
                '}';
    }
}
