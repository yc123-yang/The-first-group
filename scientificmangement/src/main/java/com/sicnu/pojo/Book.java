package com.sicnu.pojo;


import java.sql.Timestamp;

public class Book {

    private Integer bookId;
    private Integer leaderId;
    private String bookName;
    private String press;
    private Integer plId;
    private Integer btId;
    private Integer ppId;
    private String isbn;
    private String wordNumber;
    private String trans;
    private Integer languageId;
    private String scId;
    private String subjectId;
    private java.sql.Timestamp publishTime;
    private Integer aodId;
    private Integer sdId;
    private Integer rtId;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Integer leaderId) {
        this.leaderId = leaderId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public Integer getPlId() {
        return plId;
    }

    public void setPlId(Integer plId) {
        this.plId = plId;
    }

    public Integer getBtId() {
        return btId;
    }

    public void setBtId(Integer btId) {
        this.btId = btId;
    }

    public Integer getPpId() {
        return ppId;
    }

    public void setPpId(Integer ppId) {
        this.ppId = ppId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getWordNumber() {
        return wordNumber;
    }

    public void setWordNumber(String wordNumber) {
        this.wordNumber = wordNumber;
    }

    public String getTrans() {
        return trans;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public String getScId() {
        return scId;
    }

    public void setScId(String scId) {
        this.scId = scId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public Timestamp getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getAodId() {
        return aodId;
    }

    public void setAodId(Integer aodId) {
        this.aodId = aodId;
    }

    public Integer getSdId() {
        return sdId;
    }

    public void setSdId(Integer sdId) {
        this.sdId = sdId;
    }

    public Integer getRtId() {
        return rtId;
    }

    public void setRtId(Integer rtId) {
        this.rtId = rtId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", leaderId=" + leaderId +
                ", bookName='" + bookName + '\'' +
                ", press='" + press + '\'' +
                ", plId=" + plId +
                ", btId=" + btId +
                ", ppId=" + ppId +
                ", isbn='" + isbn + '\'' +
                ", wordNumber='" + wordNumber + '\'' +
                ", trans='" + trans + '\'' +
                ", languageId=" + languageId +
                ", scId='" + scId + '\'' +
                ", subjectId='" + subjectId + '\'' +
                ", publishTime=" + publishTime +
                ", aodId=" + aodId +
                ", sdId=" + sdId +
                ", rtId=" + rtId +
                '}';
    }
}
