
package com.king.urban.common.util;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

/**
 * 非对称RSA加密解密工具
 *
 * @author liukai
 */
public class RSAUtil {

    public static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCQAyQthHPJu9yzayAGnWUB2y6NFFWHwPm+VJGB4C0dRkm4apmkI7CVT/49HrxUmGplHkb3c1QV9Gq2WtZcE4oKbnA0hGaJWYG+zAxYOvDPGcS0Crqs/qo2d4wjXPhYDncv9sWhkOqL4nUg2nIBHCA4lNROpuKJpCgNDYvhndE+DwIDAQAB";
    public static final String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJADJC2Ec8m73LNrIAadZQHbLo0UVYfA+b5UkYHgLR1GSbhqmaQjsJVP/j0evFSYamUeRvdzVBX0arZa1lwTigpucDSEZolZgb7MDFg68M8ZxLQKuqz+qjZ3jCNc+FgOdy/2xaGQ6ovidSDacgEcIDiU1E6m4omkKA0Ni+Gd0T4PAgMBAAECgYAQQEJ/XnCAhdIQbxjEllNVI8o7gl1qfdTQ7RMkcEs2//0GsgTXeG2PU+ERPZwsAa0T7xg8CUgmWBgIrvR8nHnZqmSOof5Pfp4ZttLducxcBmaouq4TguKfIefHjAUWoEIvPfEGEhZCZe6PGgcpp1aGuh4uux6xMN4zKk5j4NTGlQJBAJ8JcGjuDUbJRRLFmn9VTa2bGShc50ylZhOhuKtq8E7rdd5GHMljJKlEVTzoSyPV3BnmNrNOPBY6xrpbNAWROj0CQQDn0KeHxISx5blVJdZWwHbAetfqzUwXirlaRz2CgUjkyA/ecEC0M7P+iS9tNJE+Hw4JQWJQDsMosnqYKO9kMzo7AkAm6MZJxcQVx3RQzWupL5Obn9Cd4WztgaHht7VYqwc0J8NkqD6uvsTfbMp15ldL4yzAYWSMpd1piuPIADxQCGH5AkEA5zpW0XMxlS17FzbdvLzP9yDRnNFY9CaYyjdDYJjK+AGi6pL3x4fHI779QqXrtpZNbuf8Q7Tl8ANPi9VRLJZ4nwJBAI4Sifa+SoujFw5P8u+kKpMlW2O99xxAIkgrJVDcXSeNYdxViZKKp1adTz1nA+W4ACnkHja77n+z+Ptt7wymnyM=";

    private final static RSA RSA = new RSA(PRIVATE_KEY, PUBLIC_KEY);


    /**
     * 加密
     */
    public static String encrypt(String str) {
        return RSA.encryptBase64(str, KeyType.PublicKey);
    }

    /**
     * 解密
     */
    public static String decrypt(String str) {
        return RSA.decryptStr(str, KeyType.PrivateKey);
    }

}
