package com.sicnu.pojo;


import java.util.Date;

public class PatentExamine {

  private Integer pe_id;
  private Integer leader_id;
  private String patent_name;
  private Integer pt_id;
  private Integer pr_id;
  private Integer ps_id;
  private Integer aod_id;
  private String application_number;
  private Date application_time;
  private String public_number;
  private Date public_time;
  private String authorization_number;
  private Date authorization_time;
  private String examine_status;
  private Date apply_time;
  private Integer reviewer_id;

  public Integer getPe_id() {
    return pe_id;
  }

  public void setPe_id(Integer pe_id) {
    this.pe_id = pe_id;
  }

  public Integer getLeader_id() {
    return leader_id;
  }

  public void setLeader_id(Integer leader_id) {
    this.leader_id = leader_id;
  }

  public String getPatent_name() {
    return patent_name;
  }

  public void setPatent_name(String patent_name) {
    this.patent_name = patent_name;
  }

  public Integer getPt_id() {
    return pt_id;
  }

  public void setPt_id(Integer pt_id) {
    this.pt_id = pt_id;
  }

  public Integer getPr_id() {
    return pr_id;
  }

  public void setPr_id(Integer pr_id) {
    this.pr_id = pr_id;
  }

  public Integer getPs_id() {
    return ps_id;
  }

  public void setPs_id(Integer ps_id) {
    this.ps_id = ps_id;
  }

  public Integer getAod_id() {
    return aod_id;
  }

  public void setAod_id(Integer aod_id) {
    this.aod_id = aod_id;
  }

  public String getApplication_number() {
    return application_number;
  }

  public void setApplication_number(String application_number) {
    this.application_number = application_number;
  }

  public Date getApplication_time() {
    return application_time;
  }

  public void setApplication_time(Date application_time) {
    this.application_time = application_time;
  }

  public String getPublic_number() {
    return public_number;
  }

  public void setPublic_number(String public_number) {
    this.public_number = public_number;
  }

  public Date getPublic_time() {
    return public_time;
  }

  public void setPublic_time(Date public_time) {
    this.public_time = public_time;
  }

  public String getAuthorization_number() {
    return authorization_number;
  }

  public void setAuthorization_number(String authorization_number) {
    this.authorization_number = authorization_number;
  }

  public Date getAuthorization_time() {
    return authorization_time;
  }

  public void setAuthorization_time(Date authorization_time) {
    this.authorization_time = authorization_time;
  }

  public String getExamine_status() {
    return examine_status;
  }

  public void setExamine_status(String examine_status) {
    this.examine_status = examine_status;
  }

  public Date getApply_time() {
    return apply_time;
  }

  public void setApply_time(Date apply_time) {
    this.apply_time = apply_time;
  }

  public Integer getReviewer_id() {
    return reviewer_id;
  }

  public void setReviewer_id(Integer reviewer_id) {
    this.reviewer_id = reviewer_id;
  }
}
