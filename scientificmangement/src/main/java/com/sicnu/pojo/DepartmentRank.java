package com.sicnu.pojo;

/**
 * 单位排名
 */
public class DepartmentRank {
    private Integer dr_id;
    private String dr_name;

    public Integer getDr_id() {
        return dr_id;
    }

    public void setDr_id(Integer dr_id) {
        this.dr_id = dr_id;
    }

    public String getDr_name() {
        return dr_name;
    }

    public void setDr_name(String dr_name) {
        this.dr_name = dr_name;
    }

    @Override
    public String toString() {
        return "DepartmentRank{" +
                "dr_id=" + dr_id +
                ", dr_name='" + dr_name + '\'' +
                '}';
    }
}
