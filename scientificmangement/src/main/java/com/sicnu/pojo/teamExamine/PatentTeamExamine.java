package com.sicnu.pojo.teamExamine;


public class PatentTeamExamine {

  private Integer patent_id;
  private Integer user_id;
  private String user_role;
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

  public String getUser_role() {
    return user_role;
  }

  public void setUser_role(String user_role) {
    this.user_role = user_role;
  }

  public Double getContribution() {
    return contribution;
  }

  public void setContribution(Double contribution) {
    this.contribution = contribution;
  }
}
