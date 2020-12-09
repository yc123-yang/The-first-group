package com.sicnu.pojo;

/**
 * 出版社
 */
public class PressLevel {
    private Integer pl_id;
    private String pl_name;
    private Integer bpl_score;

    public Integer getBpl_score() {
        return bpl_score;
    }

    public void setBpl_score(Integer bpl_score) {
        this.bpl_score = bpl_score;
    }

    public Integer getPl_id() {
        return pl_id;
    }

    public void setPl_id(Integer pl_id) {
        this.pl_id = pl_id;
    }

    public String getPl_name() {
        return pl_name;
    }

    public void setPl_name(String pl_name) {
        this.pl_name = pl_name;
    }


}
