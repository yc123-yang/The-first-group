package com.sicnu.pojo;

import java.util.Date;

public class Check_Email {
    private String email;
    private Integer check_code;
    private Date overtime;

    public Check_Email() {
    }

    public Check_Email(String email, Integer check_code, Date overtime) {
        this.email = email;
        this.check_code = check_code;
        this.overtime = overtime;
    }

}
