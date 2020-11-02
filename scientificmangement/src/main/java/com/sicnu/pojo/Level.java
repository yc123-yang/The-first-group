package com.sicnu.pojo;

import java.io.Serializable;

public class Level implements Serializable {
    private Integer level_id;
    private String nature_name;

    public Level(Integer level_id, String nature_name) {

        this.level_id = level_id;
        this.nature_name = nature_name;
    }

    public Integer getLevel_id() {
        return level_id;
    }

    public void setLevel_id(Integer level_id) {
        this.level_id = level_id;
    }

    public String getNature_name() {
        return nature_name;
    }

    public void setNature_name(String nature_name) {
        this.nature_name = nature_name;
    }

    @Override
    public String toString() {
        return "Level{" +
                "level_id=" + level_id +
                ", nature_name='" + nature_name + '\'' +
                '}';
    }
}
