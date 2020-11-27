package com.sicnu.pojo;

/**
 * 出版地
 */
public class PublicationPlace {
    private Integer pp_id;
    private String pp_name;

    public Integer getPp_id() {
        return pp_id;
    }

    public void setPp_id(Integer pp_id) {
        this.pp_id = pp_id;
    }

    public String getPp_name() {
        return pp_name;
    }

    public void setPp_name(String pp_name) {
        this.pp_name = pp_name;
    }

    @Override
    public String toString() {
        return "PublicationPlace{" +
                "pp_id=" + pp_id +
                ", pp_name=" + pp_name +
                '}';
    }
}
