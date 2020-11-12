package com.sicnu.pojo;

import java.io.Serializable;

/**
 * 角色
 */
public class Role implements Serializable {
    private Integer role_id;
    private String role_name;

    public Role(Integer role_id, String role_name) {
        this.role_id = role_id;
        this.role_name = role_name;
    }

    public Role() {
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role_id=" + role_id +
                ", role_name='" + role_name + '\'' +
                '}';
    }
}
