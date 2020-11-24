package com.sicnu.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 权限
 */
public class Auth implements Serializable {

    private Integer auth_id;
    private String auth_name;
    private Integer auth_pid;
    private String auth_resource;
    private Integer open;
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

    public Integer getAuth_pid() {
        return auth_pid;
    }

    public void setAuth_pid(Integer auth_pid) {
        this.auth_pid = auth_pid;
    }

    public String getAuth_resource() {
        return auth_resource;
    }

    public void setAuth_resource(String auth_resource) {
        this.auth_resource = auth_resource;
    }

    public Integer getOpen() {
        return open;
    }

    public void setOpen(Integer open) {
        this.open = open;
    }

    @Override
    public String toString() {
        return "Auth{" +
                "auth_id=" + auth_id +
                ", auth_name='" + auth_name + '\'' +
                ", auth_pid=" + auth_pid +
                ", auth_resource='" + auth_resource + '\'' +
                ", open=" + open +
                ", roleAuths=" + roleAuths +
                '}';
    }
}
