package com.sicnu.pojo.teamMap;

public class AwardTeamMap {
    private String name;
    private Integer department_id;
    private Integer role_id;
    private Integer contribution;
    private Integer user_id;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getContribution() {
        return contribution;
    }

    public void setContribution(Integer contribution) {
        this.contribution = contribution;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}