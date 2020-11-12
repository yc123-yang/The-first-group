package com.sicnu.pojo;

import java.util.Date;

/**
 * 验证码
 */
public class CheckEmail {
    private String email;
    private Integer check_code;
    private Date overtime;

    public CheckEmail() {
    }

    public CheckEmail(String email, Integer check_code, Date overtime) {
        this.email = email;
        this.check_code = check_code;
        this.overtime = overtime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCheck_code() {
        return check_code;
    }

    public void setCheck_code(Integer check_code) {
        this.check_code = check_code;
    }

    public Date getOvertime() {
        return overtime;
    }

    public void setOvertime(Date overtime) {
        this.overtime = overtime;
    }

    @Override
    public String toString() {
        return "Check_Email{" +
                "email='" + email + '\'' +
                ", check_code=" + check_code +
                ", overtime=" + overtime +
                '}';
    }
}
