package com.wengyingjian.kylin.util;

import java.security.MessageDigest;

/**
 * 加密工具类
 * Created by wengyingjian on 16/2/16.
 */
public class EncryptUtil {

    public static String SHA1(String source) {
        if (source == null) {
            return null;
        }

        StringBuffer hexString = new StringBuffer();
        try {
            MessageDigest digest = MessageDigest
                    .getInstance("SHA-1");
            digest.update(source.getBytes());
            byte messageDigest[] = digest.digest();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
        } catch (Exception e) {
        }
        return hexString.toString();
    }
}
