package com.king.urban.security.web.principal;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

    public Authority() {
    }

    @Override
    public String getAuthority() {
        return "ROLE_ADMIN";
    }

}
