package com.sicnu.pojo;


import java.io.Serializable;
import java.util.Date;

/**
 * 专利
 */
public class Patent implements Serializable {

  private long patentId;
  private String patentName;
  private long ptId;
  private long prId;
  private long psId;
  private long aodId;
  private String applicationNumber;
  private Date applicationTime;
  private String publicNumber;
  private Date publicTime;
  private String authorizationNumber;
  private Date authorizationTime;


  public long getPatentId() {
    return patentId;
  }

  public void setPatentId(long patentId) {
    this.patentId = patentId;
  }


  public String getPatentName() {
    return patentName;
  }

  public void setPatentName(String patentName) {
    this.patentName = patentName;
  }


  public long getPtId() {
    return ptId;
  }

  public void setPtId(long ptId) {
    this.ptId = ptId;
  }


  public long getPrId() {
    return prId;
  }

  public void setPrId(long prId) {
    this.prId = prId;
  }


  public long getPsId() {
    return psId;
  }

  public void setPsId(long psId) {
    this.psId = psId;
  }


  public long getAodId() {
    return aodId;
  }

  public void setAodId(long aodId) {
    this.aodId = aodId;
  }


  public String getApplicationNumber() {
    return applicationNumber;
  }

  public void setApplicationNumber(String applicationNumber) {
    this.applicationNumber = applicationNumber;
  }


  public Date getApplicationTime() {
    return applicationTime;
  }

  public void setApplicationTime(Date applicationTime) {
    this.applicationTime = applicationTime;
  }


  public String getPublicNumber() {
    return publicNumber;
  }

  public void setPublicNumber(String publicNumber) {
    this.publicNumber = publicNumber;
  }


  public Date getPublicTime() {
    return publicTime;
  }

  public void setPublicTime(Date publicTime) {
    this.publicTime = publicTime;
  }


  public String getAuthorizationNumber() {
    return authorizationNumber;
  }

  public void setAuthorizationNumber(String authorizationNumber) {
    this.authorizationNumber = authorizationNumber;
  }


  public Date getAuthorizationTime() {
    return authorizationTime;
  }

  public void setAuthorizationTime(Date authorizationTime) {
    this.authorizationTime = authorizationTime;
  }

  @Override
  public String toString() {
    return "Patent{" +
            "patentId=" + patentId +
            ", patentName='" + patentName + '\'' +
            ", ptId=" + ptId +
            ", prId=" + prId +
            ", psId=" + psId +
            ", aodId=" + aodId +
            ", applicationNumber='" + applicationNumber + '\'' +
            ", applicationTime=" + applicationTime +
            ", publicNumber='" + publicNumber + '\'' +
            ", publicTime=" + publicTime +
            ", authorizationNumber='" + authorizationNumber + '\'' +
            ", authorizationTime=" + authorizationTime +
            '}';
  }
}
