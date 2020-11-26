package com.sicnu.pojo;

/**
 * 出版社
 */
public class PressLevel {
    private Integer pl_id;
    private String pl_name;

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

    @Override
    public String toString() {
        return "PressLevel{" +
                "pl_id=" + pl_id +
                ", pl_name='" + pl_name + '\'' +
                '}';
    }
}
