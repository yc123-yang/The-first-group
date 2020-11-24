package com.sicnu.pojo;

import java.io.Serializable;

/**
 * 用户表
 */
public class User implements Serializable {
    private Integer user_id;
    private String user_act;
    private String user_pwd;
    private String user_name;
    private String user_email;
    private String user_number;
    private String user_id_number;
    private Integer user_state;
    private Integer department_id;
    private Integer role_id;
    private String salt;

    public User() {
    }


    public User(Integer user_id, String user_act, String user_pwd, String user_name, String user_email, String user_number, String user_id_number, Integer user_state, Integer department_id, Integer role_id, String salt) {
        this.user_id = user_id;
        this.user_act = user_act;
        this.user_pwd = user_pwd;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_number = user_number;
        this.user_id_number = user_id_number;
        this.user_state = user_state;
        this.department_id = department_id;
        this.role_id = role_id;
        this.salt = salt;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_act() {
        return user_act;
    }

    public void setUser_act(String user_act) {
        this.user_act = user_act;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_number() {
        return user_number;
    }

    public void setUser_number(String user_number) {
        this.user_number = user_number;
    }

    public String getUser_id_number() {
        return user_id_number;
    }

    public void setUser_id_number(String user_id_number) {
        this.user_id_number = user_id_number;
    }

    public Integer getUser_state() {
        return user_state;
    }

    public void setUser_state(Integer user_state) {
        this.user_state = user_state;
    }

    public Integer getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Integer department_id) {
        this.department_id = department_id;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_act='" + user_act + '\'' +
                ", user_pwd='" + user_pwd + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_email='" + user_email + '\'' +
                ", user_number='" + user_number + '\'' +
                ", user_id_number='" + user_id_number + '\'' +
                ", user_state=" + user_state +
                ", department_id=" + department_id +
                ", role_id=" + role_id +
                ", salt='" + salt + '\'' +
                '}';
    }
}
