package com.sicnu.pojo;
/*
专利类型
 */
public class PatentType {
    private Integer pt_id;
    private  String pt_name;

    public Integer getPt_id() {
        return pt_id;
    }

    public void setPt_id(Integer pt_id) {
        this.pt_id = pt_id;
    }

    public String getPt_name() {
        return pt_name;
    }

    public void setPt_name(String pt_name) {
        this.pt_name = pt_name;
    }

    @Override
    public String toString() {
        return "PatentType{" +
                "pt_id=" + pt_id +
                ", pt_name='" + pt_name + '\'' +
                '}';
    }
}
