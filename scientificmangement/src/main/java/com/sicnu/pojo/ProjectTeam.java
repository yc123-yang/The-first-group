package com.sicnu.pojo;

import java.util.Date;
import java.util.List;

/**
 * 项目组
 */
public class ProjectTeam {
    private Integer project_id;
    private Integer user_id;
    private String team_role;

    private List<User> users;

    public ProjectTeam() {
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

    public String getTeam_role() {
        return team_role;
    }

    public void setTeam_role(String team_role) {
        this.team_role = team_role;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}