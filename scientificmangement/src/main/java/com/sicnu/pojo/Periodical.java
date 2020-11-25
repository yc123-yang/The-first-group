package com.sicnu.pojo;

/**
 * 期刊
 */
public class Periodical {
    private Integer periodical_id;
    private String periodical_name;

    public Integer getPeriodical_id() {
        return periodical_id;
    }

    public void setPeriodical_id(Integer periodical_id) {
        this.periodical_id = periodical_id;
    }

    public String getPeriodical_name() {
        return periodical_name;
    }

    public void setPeriodical_name(String periodical_name) {
        this.periodical_name = periodical_name;
    }

    @Override
    public String toString() {
        return "Periodical{" +
                "periodical_id=" + periodical_id +
                ", periodical_name='" + periodical_name + '\'' +
                '}';
    }
}
