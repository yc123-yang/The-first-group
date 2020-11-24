package com.sicnu.util;

import com.sicnu.pojo.User;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordMd {
    private final String algorithmName = "MD5";
    private final int hashIterations = 2;

    public void encryptPassword(User user) {
        //String salt = randomNumberGenerator.nextBytes().toHex();
        String newPassword = new SimpleHash(algorithmName, user.getUser_pwd(),
                ByteSource.Util.bytes(user.getUser_act()), hashIterations).toHex();
        //String newPassword = new SimpleHash(algorithmName, user.getPassword()).toHex();
        user.setUser_pwd(newPassword);
    }
}





