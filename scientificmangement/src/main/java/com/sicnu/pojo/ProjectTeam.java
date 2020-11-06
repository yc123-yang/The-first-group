package com.sicnu.pojo;

import java.util.Date;
import java.util.List;

public class ProjectTeam {
    private Integer project_id;
    private Integer user_id;
    private String user_role;

    private List<User> users ;
    public ProjectTeam() {
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    @Override
    public String toString() {
        return "ProjectTeam{" +
                "project_id=" + project_id +
                ", user_id=" + user_id +
                ", user_role='" + user_role + '\'' +
                ", users=" + users +
                '}';
    }
}