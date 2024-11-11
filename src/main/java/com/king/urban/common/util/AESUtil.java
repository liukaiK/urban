package com.king.urban.common.util;

import cn.dev33.satoken.secure.SaSecureUtil;

/**
 * AES对称加密
 */
public class AESUtil {


    private final static String KEY = "1671753902917402624";

    /**
     * 加密
     */
    public static String encrypt(String text) {
        return SaSecureUtil.aesEncrypt(KEY, text);
    }

    /**
     * 解密
     */
    public static String decrypt(String text) {
        return SaSecureUtil.aesDecrypt(KEY, text);
    }


}
