package com.sicnu.pojo;


import java.util.Date;

/**
 * 著作
 */
public class Book {

  private Integer book_id;
  private Integer leader_id;
  private String book_name;
  private String press;
  private Integer pl_id;
  private Integer bt_id;
  private Integer pp_id;
  private String isbn;
  private String word_number;
  private String trans;
  private Integer language_id;
  private String sc_id;
  private String subject_id;
  private Date publish_time;
  private Integer aod_id;
  private Integer sd_id;
  private Integer rt_id;

  public Integer getBook_id() {
    return book_id;
  }

  public void setBook_id(Integer book_id) {
    this.book_id = book_id;
  }

  public Integer getLeader_id() {
    return leader_id;
  }

  public void setLeader_id(Integer leader_id) {
    this.leader_id = leader_id;
  }

  public String getBook_name() {
    return book_name;
  }

  public void setBook_name(String book_name) {
    this.book_name = book_name;
  }

  public String getPress() {
    return press;
  }

  public void setPress(String press) {
    this.press = press;
  }

  public Integer getPl_id() {
    return pl_id;
  }

  public void setPl_id(Integer pl_id) {
    this.pl_id = pl_id;
  }

  public Integer getBt_id() {
    return bt_id;
  }

  public void setBt_id(Integer bt_id) {
    this.bt_id = bt_id;
  }

  public Integer getPp_id() {
    return pp_id;
  }

  public void setPp_id(Integer pp_id) {
    this.pp_id = pp_id;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getWord_number() {
    return word_number;
  }

  public void setWord_number(String word_number) {
    this.word_number = word_number;
  }

  public String getTrans() {
    return trans;
  }

  public void setTrans(String trans) {
    this.trans = trans;
  }

  public Integer getLanguage_id() {
    return language_id;
  }

  public void setLanguage_id(Integer language_id) {
    this.language_id = language_id;
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

  public Date getPublish_time() {
    return publish_time;
  }

  public void setPublish_time(Date publish_time) {
    this.publish_time = publish_time;
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

  public Integer getRt_id() {
    return rt_id;
  }

  public void setRt_id(Integer rt_id) {
    this.rt_id = rt_id;
  }

  @Override
  public String toString() {
    return "Book{" +
            "book_id=" + book_id +
            ", leader_id=" + leader_id +
            ", book_name='" + book_name + '\'' +
            ", press='" + press + '\'' +
            ", pl_id=" + pl_id +
            ", bt_id=" + bt_id +
            ", pp_id=" + pp_id +
            ", isbn='" + isbn + '\'' +
            ", word_number='" + word_number + '\'' +
            ", trans='" + trans + '\'' +
            ", language_id=" + language_id +
            ", sc_id='" + sc_id + '\'' +
            ", subject_id='" + subject_id + '\'' +
            ", publish_time=" + publish_time +
            ", aod_id=" + aod_id +
            ", sd_id=" + sd_id +
            ", rt_id=" + rt_id +
            '}';
  }
}
