package com.sicnu.pojo;

public class SystemLog {

  private Integer sl_id;
  private String sl_level;
  private String sl_location;
  private String sl_time;
  private String sl_message;


  public Integer getSl_id() {
    return sl_id;
  }

  public void setSl_id(Integer sl_id) {
    this.sl_id = sl_id;
  }

  public String getSl_level() {
    return sl_level;
  }

  public void setSl_level(String sl_level) {
    this.sl_level = sl_level;
  }

  public String getSl_location() {
    return sl_location;
  }

  public void setSl_location(String sl_location) {
    this.sl_location = sl_location;
  }

  public String getSl_time() {
    return sl_time;
  }

  public void setSl_time(String sl_time) {
    this.sl_time = sl_time;
  }

  public String getSl_message() {
    return sl_message;
  }

  public void setSl_message(String sl_message) {
    this.sl_message = sl_message;
  }
}
