package com.sicnu.pojo;


public class PaperTeam {

  private Integer paper_id;
  private Integer user_id;
  private String user_role;
  private Integer contribution;

  public Integer getContribution() {
    return contribution;
  }

  public void setContribution(Integer contribution) {
    this.contribution = contribution;
  }



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

  public String getUser_role() {
    return user_role;
  }

  public void setUser_role(String user_role) {
    this.user_role = user_role;
  }

}
