package com.sicnu.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Project implements Serializable {

    private Integer project_id;
    private String project_stage;
    private String project_type;
    private Integer leader_id;
    private String project_name;
    private String project_abstract;
    private String declaration;
    private Date apply_time;

    private List<Project_Team> projectTeams;

    public List<Project_Team> getProjectTeams() {
        return projectTeams;
    }

    public void setProjectTeams(List<Project_Team> projectTeams) {
        this.projectTeams = projectTeams;
    }

    public Project() {
    }



    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }

    public String getProject_stage() {
        return project_stage;
    }

    public void setProject_stage(String project_stage) {
        this.project_stage = project_stage;
    }

    public String getProject_type() {
        return project_type;
    }

    public void setProject_type(String project_type) {
        this.project_type = project_type;
    }

    public Integer getLeader_id() {
        return leader_id;
    }

    public void setLeader_id(Integer leader_id) {
        this.leader_id = leader_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_abstract() {
        return project_abstract;
    }

    public void setProject_abstract(String project_abstract) {
        this.project_abstract = project_abstract;
    }

    public String getDeclaration() {
        return declaration;
    }

    public void setDeclaration(String declaration) {
        this.declaration = declaration;
    }

    public Date getApply_time() {
        return apply_time;
    }

    public void setApply_time(Date apply_time) {
        this.apply_time = apply_time;
    }

    @Override
    public String toString() {
        return "Project{" +
                "project_id=" + project_id +
                ", project_stage='" + project_stage + '\'' +
                ", project_type='" + project_type + '\'' +
                ", leader_id=" + leader_id +
                ", project_name='" + project_name + '\'' +
                ", project_abstract='" + project_abstract + '\'' +
                ", declaration='" + declaration + '\'' +
                ", apply_time=" + apply_time +
                ", projectTeams=" + projectTeams +
                '}';
    }
}
