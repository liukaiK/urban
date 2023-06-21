package com.king.urban.security.web.authentication;

import cn.hutool.crypto.CryptoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author liukai
 */
public class UserDetailsAuthenticationProvider implements AuthenticationProvider {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private UserDetailsService userDetailsService;

    private PasswordEncoder passwordEncoder;

//    private RedisUtils redisUtils;

    private final GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // Determine username
        String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();

        UserDetails user;

        try {
            if (false) {
                checkLocked(username);
            }
            user = userDetailsService.loadUserByUsername(username);
            preAuthenticationChecks(user);
            additionalAuthenticationChecks(user, (UsernamePasswordCaptchaAuthenticationToken) authentication);
        } catch (AuthenticationException exception) {
            log.debug("账号{}认证失败", username, exception);
            throw exception;
        } catch (CryptoException exception) {
            log.error("username:{} RSA解析密码出现错误", username);
            throw new BadCredentialsException("RSA解析密码出现错误");
        }


        return createSuccessAuthentication(user, authentication, user);
    }

    /**
     * 检查账号是否被锁定
     */
    private void checkLocked(String username) {
//        String key = "user::" + username + "::locked";
//        boolean hasKey = redisUtils.hasKey(key);
//        if (hasKey) {
//            throw new LockedException("账户已经被锁定");
//        }
    }


    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user) {
        UsernamePasswordCaptchaAuthenticationToken result = new UsernamePasswordCaptchaAuthenticationToken(principal, authentication.getCredentials(), authoritiesMapper.mapAuthorities(user.getAuthorities()));
        result.setDetails(authentication.getDetails());
        return result;
    }


    /**
     * 校验用户是否被锁定 或禁用等功能
     */
    private void preAuthenticationChecks(UserDetails user) {
        if (!user.isEnabled()) {
            throw new DisabledException("账户未激活");
        }
    }


    /**
     * 校验密码
     */
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordCaptchaAuthenticationToken authentication) throws AuthenticationException {

        if (authentication.getCredentials() == null) {
            throw new BadCredentialsException("必须提供密码");
        }
//
//        String originPassword = getOriginPassword(authentication.getCredentials().toString());
//
//        if (!passwordEncoder.matches(originPassword, userDetails.getPassword())) {
//            throw new BadCredentialsException("密码匹配失败");
//        }


    }


    /**
     * 将前台传来的密码使用RSA解密
     */
//    private String getOriginPassword(String decrypt) {
//        return RSAUtil.decrypt(decrypt);
//    }
    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordCaptchaAuthenticationToken.class.isAssignableFrom(authentication));
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

}
