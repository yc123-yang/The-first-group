package com.sicnu.pojo;

/**
 * 专利范围
 */
public class PatentRange {
    private Integer pr_id;
    private String pr_name;

    public Integer getPr_id() {
        return pr_id;
    }

    public void setPr_id(Integer pr_id) {
        this.pr_id = pr_id;
    }

    public String getPr_name() {
        return pr_name;
    }

    public void setPr_name(String pr_name) {
        this.pr_name = pr_name;
    }

    @Override
    public String toString() {
        return "PatentRange{" +
                "pr_id=" + pr_id +
                ", pr_name='" + pr_name + '\'' +
                '}';
    }
}
