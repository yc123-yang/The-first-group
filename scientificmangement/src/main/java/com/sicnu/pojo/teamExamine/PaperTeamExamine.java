package com.sicnu.pojo.teamExamine;


public class PaperTeamExamine {

  private Integer paper_id;
  private Integer user_id;
  private Integer role_id;
  private Double contribution;


  public Integer getPaper_id() {
    return paper_id;
  }

  public void setPaper_id(Integer paper_id) {
    this.paper_id = paper_id;
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
