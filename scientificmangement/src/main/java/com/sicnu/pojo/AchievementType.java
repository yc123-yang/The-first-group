package com.sicnu.pojo;

/**
 * 成果
 */
public class AchievementType {
    private Integer at_id;
    private String at_name;

    public AchievementType(Integer at_id, String at_name) {
        this.at_id = at_id;
        this.at_name = at_name;
    }

    public Integer getAt_id() {
        return at_id;
    }

    public void setAt_id(Integer ac_id) {
        this.at_id = ac_id;
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
                "at_id=" + at_id +
                ", at_name='" + at_name + '\'' +
                '}';
    }
}
