package com.sicnu.pojo;

import java.util.Date;
import java.util.List;

public class CheckTeam {
    private Integer project_id;
    private Integer user_id;
    private Date join_time;

    private List<User> users ;
    public CheckTeam() {
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

    public Date getJoin_time() {
        return join_time;
    }

    public void setJoin_time(Date join_time) {
        this.join_time = join_time;
    }

    @Override
    public String toString() {
        return "Check_Team{" +
                "project_id=" + project_id +
                ", user_id=" + user_id +
                ", join_time=" + join_time +
                ", users=" + users +
                '}';
    }
}
