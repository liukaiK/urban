package com.king.urban.security.web.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UsernamePasswordCaptchaAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;

    private Object credentials;


    public UsernamePasswordCaptchaAuthenticationToken(Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(false);
    }

    public UsernamePasswordCaptchaAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true);
    }


    public static UsernamePasswordCaptchaAuthenticationToken unauthenticated(Object principal, Object credentials) {
        return new UsernamePasswordCaptchaAuthenticationToken(principal, credentials);
    }


    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
