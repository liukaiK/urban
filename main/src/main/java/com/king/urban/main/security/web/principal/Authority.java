package com.king.urban.main.security.web.principal;

import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;

public class Authority implements GrantedAuthority {

    private String authority;

    public Authority() {
    }

    @Override
    public String getAuthority() {
        return "ROLE_ADMIN";
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority1 = (Authority) o;
        return Objects.equals(authority, authority1.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authority);
    }

}
