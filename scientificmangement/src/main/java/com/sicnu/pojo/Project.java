package com.sicnu.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 项目信息
 */
public class Project implements Serializable {


    private Integer project_id;
    private String project_name;
    private Integer leader_id;
    private Integer department_id;
    private Integer aod_id;
    private String sc_id;
    private String subject_id;
    private Integer nature_id;
    private Integer level_id;
    private Integer status_id;
    private Integer sd_id;
    private Integer at_id;
    private String approval_number;
    private Date start_time;
    private Date plan_time;
    private Date complete_time;
    private Integer outlay;
    private Integer ct_id;
    private List<ProjectTeam> projectTeams;

    public Project() {
    }

    public Project(Integer project_id, String project_name, Integer leader_id, Integer department_id, Integer aod_id, String sc_id, String subject_id, Integer nature_id, Integer level_id, Integer status_id, Integer sd_id, Integer at_id, String approval_number, Date start_time, Date plan_time, Date complete_time, Integer outlay, Integer ct_id) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.leader_id = leader_id;
        this.department_id = department_id;
        this.aod_id = aod_id;
        this.sc_id = sc_id;
        this.subject_id = subject_id;
        this.nature_id = nature_id;
        this.level_id = level_id;
        this.status_id = status_id;
        this.sd_id = sd_id;
        this.at_id = at_id;
        this.approval_number = approval_number;
        this.start_time = start_time;
        this.plan_time = plan_time;
        this.complete_time = complete_time;
        this.outlay = outlay;
        this.ct_id = ct_id;
    }

    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public Integer getLeader_id() {
        return leader_id;
    }

    public void setLeader_id(Integer leader_id) {
        this.leader_id = leader_id;
    }

    public Integer getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Integer department_id) {
        this.department_id = department_id;
    }

    public Integer getAod_id() {
        return aod_id;
    }

    public void setAod_id(Integer aod_id) {
        this.aod_id = aod_id;
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

    public Integer getNature_id() {
        return nature_id;
    }

    public void setNature_id(Integer nature_id) {
        this.nature_id = nature_id;
    }

    public Integer getLevel_id() {
        return level_id;
    }

    public void setLevel_id(Integer level_id) {
        this.level_id = level_id;
    }

    public Integer getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Integer status_id) {
        this.status_id = status_id;
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

    public String getApproval_number() {
        return approval_number;
    }

    public void setApproval_number(String approval_number) {
        this.approval_number = approval_number;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getPlan_time() {
        return plan_time;
    }

    public void setPlan_time(Date plan_time) {
        this.plan_time = plan_time;
    }

    public Date getComplete_time() {
        return complete_time;
    }

    public void setComplete_time(Date complete_time) {
        this.complete_time = complete_time;
    }

    public Integer getOutlay() {
        return outlay;
    }

    public void setOutlay(Integer outlay) {
        this.outlay = outlay;
    }

    public Integer getCt_id() {
        return ct_id;
    }

    public void setCt_id(Integer ct_id) {
        this.ct_id = ct_id;
    }

    public List<ProjectTeam> getProjectTeams() {
        return projectTeams;
    }

    public void setProjectTeams(List<ProjectTeam> projectTeams) {
        this.projectTeams = projectTeams;
    }


    @Override
    public String toString() {
        return "Project{" +
                "project_id=" + project_id +
                ", project_name='" + project_name + '\'' +
                ", leader_id=" + leader_id +
                ", department_id=" + department_id +
                ", aod_id=" + aod_id +
                ", sc_id=" + sc_id +
                ", subject_id=" + subject_id +
                ", nature_id=" + nature_id +
                ", level_id=" + level_id +
                ", status_id=" + status_id +
                ", sd_id=" + sd_id +
                ", at_id=" + at_id +
                ", approval_number='" + approval_number + '\'' +
                ", start_time=" + start_time +
                ", plan_time=" + plan_time +
                ", complete_time=" + complete_time +
                ", outlay=" + outlay +
                ", ct_id=" + ct_id +
                ", projectTeams=" + projectTeams +
                '}';
    }
}
