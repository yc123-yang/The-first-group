package com.sicnu.pojo;


import java.io.Serializable;

public class PatentTeamExamine implements Serializable {

    private Integer pe_id;
    private Integer user_id;
    private String user_role;
    private Integer ranking;
    private Integer contribution;

    public PatentTeamExamine() {
    }

    public Integer getPe_id() {
        return pe_id;
    }

    public void setPe_id(Integer pe_id) {
        this.pe_id = pe_id;
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

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getContribution() {
        return contribution;
    }

    public void setContribution(Integer contribution) {
        this.contribution = contribution;
    }

    @Override
    public String toString() {
        return "PatentTeamExamine{" +
                "pe_id=" + pe_id +
                ", user_id=" + user_id +
                ", user_role='" + user_role + '\'' +
                ", ranking=" + ranking +
                ", contribution=" + contribution +
                '}';
    }
}
