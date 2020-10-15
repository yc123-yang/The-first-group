package com.sicnu.util;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static String md5(String msg){
        try {
            MessageDigest md=MessageDigest.getInstance("MD5");
            byte[] input=msg.getBytes();
            byte[] output=md.digest(input);
            String s= Base64.encodeBase64String(output);
            return s;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("加密失败");
            return null;
        }
    }
}
