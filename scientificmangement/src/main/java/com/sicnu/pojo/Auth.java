package com.sicnu.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 权限
 */
public class Auth implements Serializable {

    private Integer auth_id;
    private String auth_name;
    List<RoleAuth> roleAuths;
    public Auth() {
    }

    public Auth(Integer auth_id, String auth_name) {
        this.auth_id = auth_id;
        this.auth_name = auth_name;
    }

    public Integer getAuth_id() {
        return auth_id;
    }

    public void setAuth_id(Integer auth_id) {
        this.auth_id = auth_id;
    }

    public String getAuth_name() {
        return auth_name;
    }
    public void setAuth_name(String anyh_name) {
        this.auth_name = anyh_name;
    }

    public List<RoleAuth> getRoleAuths() {
        return roleAuths;
    }

    public void setRoleAuths(List<RoleAuth> roleAuths) {
        this.roleAuths = roleAuths;
    }

    @Override
    public String toString() {
        return "Auth{" +
                "auth_id=" + auth_id +
                ", auth_name='" + auth_name + '\'' +
                ", roleAuths=" + roleAuths +
                '}';
    }
}
