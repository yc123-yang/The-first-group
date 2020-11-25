package com.sicnu.pojo;


import java.util.Date;

/**
 * 获奖
 */
public class Award {

    private Integer award_id;
    private Integer leader_id;
    private String achievement_name;
    private String award_name;
    private Date award_time;
    private String issuing_authority;
    private String approval_number;
    private Integer aod_id;
    private Integer dr_id;
    private Integer ar_id;
    private Integer al_id;
    private String sc_id;
    private String subject_id;
    private Integer sd_id;
    private Integer at_id;

    public Award() {
    }

    public Integer getAward_id() {
        return award_id;
    }

    public void setAward_id(Integer award_id) {
        this.award_id = award_id;
    }

    public Integer getLeader_id() {
        return leader_id;
    }

    public void setLeader_id(Integer leader_id) {
        this.leader_id = leader_id;
    }

    public String getAchievement_name() {
        return achievement_name;
    }

    public void setAchievement_name(String achievement_name) {
        this.achievement_name = achievement_name;
    }

    public String getAward_name() {
        return award_name;
    }

    public void setAward_name(String award_name) {
        this.award_name = award_name;
    }

    public Date getAward_time() {
        return award_time;
    }

    public void setAward_time(Date award_time) {
        this.award_time = award_time;
    }

    public String getIssuing_authority() {
        return issuing_authority;
    }

    public void setIssuing_authority(String issuing_authority) {
        this.issuing_authority = issuing_authority;
    }

    public String getApproval_number() {
        return approval_number;
    }

    public void setApproval_number(String approval_number) {
        this.approval_number = approval_number;
    }

    public Integer getAod_id() {
        return aod_id;
    }

    public void setAod_id(Integer aod_id) {
        this.aod_id = aod_id;
    }

    public Integer getDr_id() {
        return dr_id;
    }

    public void setDr_id(Integer dr_id) {
        this.dr_id = dr_id;
    }

    public Integer getAr_id() {
        return ar_id;
    }

    public void setAr_id(Integer ar_id) {
        this.ar_id = ar_id;
    }

    public Integer getAl_id() {
        return al_id;
    }

    public void setAl_id(Integer al_id) {
        this.al_id = al_id;
    }

    public String getSc_id() {
        return sc_id;
    }

    public void setSc_id(String sc_id) {
        this.sc_id = sc_id;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public Integer getSd_id() {
        return sd_id;
    }

    public void setSd_id(Integer sd_id) {
        this.sd_id = sd_id;
    }

    public Integer getAt_id() {
        return at_id;
    }

    public void setAt_id(Integer at_id) {
        this.at_id = at_id;
    }

    @Override
    public String toString() {
        return "Award{" +
                "award_id=" + award_id +
                ", leader_id=" + leader_id +
                ", achievement_name='" + achievement_name + '\'' +
                ", award_name='" + award_name + '\'' +
                ", award_time=" + award_time +
                ", issuing_authority='" + issuing_authority + '\'' +
                ", approval_number='" + approval_number + '\'' +
                ", aod_id=" + aod_id +
                ", dr_id=" + dr_id +
                ", ar_id=" + ar_id +
                ", al_id=" + al_id +
                ", sc_id='" + sc_id + '\'' +
                ", subject_id='" + subject_id + '\'' +
                ", sd_id=" + sd_id +
                ", at_id=" + at_id +
                '}';
    }
}
