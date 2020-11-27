package com.sicnu.pojo;

/**
 * 获奖级别
 */
public class AwardRank {
    private Integer ar_id;
    private String ar_name;

    public Integer getAr_id() {
        return ar_id;
    }

    public void setAr_id(Integer ar_id) {
        this.ar_id = ar_id;
    }

    public String getAr_name() {
        return ar_name;
    }

    public void setAr_name(String ar_name) {
        this.ar_name = ar_name;
    }

    @Override
    public String toString() {
        return "AwardRank{" +
                "ar_id=" + ar_id +
                ", ar_name='" + ar_name + '\'' +
                '}';
    }
}
