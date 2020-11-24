package com.sicnu.pojo;

import java.util.List;

/**
 * 角色权限
 */
public class RoleAuth {
    private Integer role_id;
    private Integer auth_id;
    List<Role> roleList;

    public RoleAuth() {
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public Integer getAuth_id() {
        return auth_id;
    }

    public void setAuth_id(Integer auth_id) {
        this.auth_id = auth_id;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "RoleAuth{" +
                "role_id=" + role_id +
                ", auth_id=" + auth_id +
                ", roleList=" + roleList +
                '}';
    }
}
