package com.sicnu.pojo.teamExamine;


public class ProjectTeamExamine {

  private Integer pe_id;
  private Integer user_id;
  private String user_role;
  private String team_role;

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

  public String getTeam_role() {
    return team_role;
  }

  public void setTeam_role(String team_role) {
    this.team_role = team_role;
  }
}
