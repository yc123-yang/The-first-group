package com.sicnu.pojo;



public class AwardTeam {

  private Integer award_id;
  private Integer user_id;
  private String user_role;
  private Double contribution;

  public long getAward_id(Integer user_id) {
    return award_id;
  }

  public void setAward_id(Integer award_id) {
    this.award_id = award_id;
  }

  public long getUser_id() {
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


  public double getContribution() {
    return contribution;
  }

  public void setContribution(double contribution) {
    this.contribution = contribution;
  }
}
