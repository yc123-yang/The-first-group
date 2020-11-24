package com.sicnu.util;

public class Code {

    public int smsCode() {
        int random = (int) ((Math.random() * 9 + 1) * 100000);
        System.out.println("创建的验证码为：" + random);
        return random;
    }
}
