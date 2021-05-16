package com.zhangmen.qa.util;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;

/**
 * barry
 */
public class MD5Utils {

    /**
     *
     * @Title: MD5Utils.java
     * @Description: 对字符串进行md5加密
     */
    public static String getMD5Str(String strValue) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        String newstr = Base64.encodeBase64String(md5.digest(strValue.getBytes()));
        return newstr;
    }


    public static String md5Lower(String input) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(input.getBytes("utf-8"));

            StringBuilder hexValue = new StringBuilder(md5Bytes.length * 2);
            for (byte md5Byte : md5Bytes) {
                int val = ((int) md5Byte) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        try {
            String md5 = getMD5Str("barry");
            System.out.println(md5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
