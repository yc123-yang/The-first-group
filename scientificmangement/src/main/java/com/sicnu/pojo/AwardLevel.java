package com.sicnu.pojo;

/**
 * 获奖的等级
 */
public class AwardLevel {
    private Integer al_id;
    private String al_name;

    public Integer getAl_id() {
        return al_id;
    }

    public void setAl_id(Integer al_id) {
        this.al_id = al_id;
    }

    public String getAl_name() {
        return al_name;
    }

    public void setAl_name(String al_name) {
        this.al_name = al_name;
    }

    @Override
    public String toString() {
        return "AwardLevel{" +
                "al_id=" + al_id +
                ", al_name='" + al_name + '\'' +
                '}';
    }
}
