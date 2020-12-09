package com.sicnu.pojo;

/**
 * 获奖的等级
 */
public class AwardLevel {
    private Integer al_id;
    private String al_name;
    private Integer al_score;

    public Integer getAl_score() {
        return al_score;
    }

    public void setAl_score(Integer al_score) {
        this.al_score = al_score;
    }

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


}
