package com.sicnu.pojo;

/**
 * 成果
 */
public class AchievementType {
    private Integer ac_id;
    private String at_name;

    public AchievementType(Integer ac_id, String at_name) {
        this.ac_id = ac_id;
        this.at_name = at_name;
    }

    public Integer getAc_id() {
        return ac_id;
    }

    public void setAc_id(Integer ac_id) {
        this.ac_id = ac_id;
    }

    public String getAt_name() {
        return at_name;
    }

    public void setAt_name(String at_name) {
        this.at_name = at_name;
    }

    @Override
    public String toString() {
        return "AchievementType{" +
                "ac_id=" + ac_id +
                ", at_name='" + at_name + '\'' +
                '}';
    }
}
