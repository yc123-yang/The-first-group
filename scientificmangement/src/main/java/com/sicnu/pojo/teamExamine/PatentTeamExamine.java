package com.sicnu.pojo.teamExamine;


public class PatentTeamExamine {

  private Integer patent_id;
  private Integer user_id;
  private Integer role_id;
  private Double contribution;


  public Integer getPatent_id() {
    return patent_id;
  }

  public void setPatent_id(Integer patent_id) {
    this.patent_id = patent_id;
  }

  public Integer getUser_id() {
    return user_id;
  }

  public void setUser_id(Integer user_id) {
    this.user_id = user_id;
  }

  public Integer getRole_id() {
    return role_id;
  }

  public void setRole_id(Integer role_id) {
    this.role_id = role_id;
  }

  public Double getContribution() {
    return contribution;
  }

  public void setContribution(Double contribution) {
    this.contribution = contribution;
  }
}
