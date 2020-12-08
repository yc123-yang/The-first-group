package com.sicnu.pojo;


public class Check{

  private Integer check_id;
  private String name;
  private Integer department_id;
  private Integer award_count;
  private Integer book_count;
  private Integer paper_count;
  private Integer patent_count;
  private Integer project_count;
  private Integer total_grade;
  private String check_time;
  private Integer user_id;

  public Integer getUser_id() {
    return user_id;
  }

  public void setUser_id(Integer user_id) {
    this.user_id = user_id;
  }

  public Integer getCheck_id() {
    return check_id;
  }

  public void setCheck_id(Integer check_id) {
    this.check_id = check_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getDepartment_id() {
    return department_id;
  }

  public void setDepartment_id(Integer department_id) {
    this.department_id = department_id;
  }

  public Integer getAward_count() {
    return award_count;
  }

  public void setAward_count(Integer award_count) {
    this.award_count = award_count;
  }

  public Integer getBook_count() {
    return book_count;
  }

  public void setBook_count(Integer book_count) {
    this.book_count = book_count;
  }

  public Integer getPaper_count() {
    return paper_count;
  }

  public void setPaper_count(Integer paper_count) {
    this.paper_count = paper_count;
  }

  public Integer getPatent_count() {
    return patent_count;
  }

  public void setPatent_count(Integer patent_count) {
    this.patent_count = patent_count;
  }

  public Integer getProject_count() {
    return project_count;
  }

  public void setProject_count(Integer project_count) {
    this.project_count = project_count;
  }

  public Integer getTotal_grade() {
    return total_grade;
  }

  public void setTotal_grade(Integer total_grade) {
    this.total_grade = total_grade;
  }

  public String getCheck_time() {
    return check_time;
  }

  public void setCheck_time(String check_time) {
    this.check_time = check_time;
  }
}
