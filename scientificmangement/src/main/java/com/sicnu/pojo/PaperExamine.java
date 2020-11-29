package com.sicnu.pojo;


import java.util.Date;

public class PaperExamine {

  private Integer pe_id;
  private Integer leader_id;
  private String paper_name;
  private Integer pt_id;
  private Integer periodical_id;
  private Date publish_time;
  private String include_number;
  private String sc_id;
  private String subject_id;
  private Integer aod_id;
  private Integer sd_id;
  private String remark;
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

  public String getPaper_name() {
    return paper_name;
  }

  public void setPaper_name(String paper_name) {
    this.paper_name = paper_name;
  }

  public Integer getPt_id() {
    return pt_id;
  }

  public void setPt_id(Integer pt_id) {
    this.pt_id = pt_id;
  }

  public Integer getPeriodical_id() {
    return periodical_id;
  }

  public void setPeriodical_id(Integer periodical_id) {
    this.periodical_id = periodical_id;
  }

  public Date getPublish_time() {
    return publish_time;
  }

  public void setPublish_time(Date publish_time) {
    this.publish_time = publish_time;
  }

  public String getInclude_number() {
    return include_number;
  }

  public void setInclude_number(String include_number) {
    this.include_number = include_number;
  }

  public String getSc_id() {
    return sc_id;
  }

  public void setSc_id(String sc_id) {
    this.sc_id = sc_id;
  }

  public String getSubject_id() {
    return subject_id;
  }

  public void setSubject_id(String subject_id) {
    this.subject_id = subject_id;
  }

  public Integer getAod_id() {
    return aod_id;
  }

  public void setAod_id(Integer aod_id) {
    this.aod_id = aod_id;
  }

  public Integer getSd_id() {
    return sd_id;
  }

  public void setSd_id(Integer sd_id) {
    this.sd_id = sd_id;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
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

  @Override
  public String toString() {
    return "PaperExamine{" +
            "pe_id=" + pe_id +
            ", leader_id=" + leader_id +
            ", paper_name='" + paper_name + '\'' +
            ", pt_id=" + pt_id +
            ", periodical_id=" + periodical_id +
            ", publish_time=" + publish_time +
            ", include_number='" + include_number + '\'' +
            ", sc_id='" + sc_id + '\'' +
            ", subject_id='" + subject_id + '\'' +
            ", aod_id=" + aod_id +
            ", sd_id=" + sd_id +
            ", remark='" + remark + '\'' +
            ", examine_status='" + examine_status + '\'' +
            ", apply_time=" + apply_time +
            ", reviewer_id=" + reviewer_id +
            '}';
  }
}
