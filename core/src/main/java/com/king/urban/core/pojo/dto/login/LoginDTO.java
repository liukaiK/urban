package com.king.urban.core.pojo.dto.login;

import lombok.Data;

@Data
public class LoginDTO {

    private String username;

    private String password;

    /**
     * 电脑登录 还是APP登录
     */
    private String type;


}
