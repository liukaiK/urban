package com.king.urban.security.web;

import cn.dev33.satoken.filter.SaFilterAuthStrategy;
import cn.dev33.satoken.filter.SaFilterErrorStrategy;
import cn.dev33.satoken.filter.SaServletFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.king.urban.common.constant.SecurityConstants;
import com.king.urban.security.web.authentication.UserDetailsAuthenticationProvider;
import com.king.urban.security.web.filter.UsernamePasswordCaptchaAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import java.util.Arrays;
import java.util.Collections;

/**
 * SpringSecurity配置与satoken一起用的
 *
 * @author liukai
 */
@EnableWebSecurity(debug = true)
public class WebSecurityConfigurer {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SessionAuthenticationStrategy sessionAuthenticationStrategy;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

//    @Bean
//    public SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
//        log.debug("----------初始化web SpringSecurity功能----------");
//        http
//                .authorizeRequests()
//                .antMatchers(SecurityConstants.LOGIN_PROCESS_URL).permitAll()
//                .anyRequest().permitAll()
//                .and()
//                .logout().logoutUrl(SecurityConstants.LOGOUT_PROCESS_URL).logoutSuccessHandler(logoutSuccessHandler)
//                .and()
//                .headers().frameOptions().sameOrigin()
//                .and()
//                .sessionManagement().disable()
//                .securityContext().disable()
//                .csrf().disable()
//                .cors()
//                .and()
//                .addFilterAt(usernamePasswordCaptchaAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(authorizationFilter(), FilterSecurityInterceptor.class)
//                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).accessDeniedHandler(accessDeniedHandler);
//        return http.build();
//    }


    @Autowired
    private SaFilterErrorStrategy saFilterErrorStrategy;

    @Autowired
    private SaFilterAuthStrategy saFilterAuthStrategy;

//    private SaServletFilter authorizationFilter() {
//        SaServletFilter saServletFilter = new SaServletFilter();
//        saServletFilter.setIncludeList(Collections.singletonList(SecurityConstants.AUTH_URL));
//        saServletFilter.setExcludeList(Arrays.asList(SecurityConstants.IGNORING_URL));
//        saServletFilter.setAuth(saFilterAuthStrategy);
//        saServletFilter.setError(saFilterErrorStrategy);
//        return saServletFilter;
//    }

    public UsernamePasswordCaptchaAuthenticationFilter usernamePasswordCaptchaAuthenticationFilter() {
        UsernamePasswordCaptchaAuthenticationFilter usernamePasswordCaptchaAuthenticationFilter = new UsernamePasswordCaptchaAuthenticationFilter();
        usernamePasswordCaptchaAuthenticationFilter.setAuthenticationManager(authenticationManager());
        usernamePasswordCaptchaAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        usernamePasswordCaptchaAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        usernamePasswordCaptchaAuthenticationFilter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy);
        usernamePasswordCaptchaAuthenticationFilter.setObjectMapper(objectMapper);
        return usernamePasswordCaptchaAuthenticationFilter;
    }

    private AuthenticationManager authenticationManager() {
        ProviderManager providerManager = new ProviderManager(userDetailsAuthenticationProvider());
        providerManager.setAuthenticationEventPublisher(authenticationEventPublisher());
        return providerManager;
    }


    public AuthenticationProvider userDetailsAuthenticationProvider() {
        return new UserDetailsAuthenticationProvider(userDetailsService, passwordEncoder);
    }

    public AuthenticationEventPublisher authenticationEventPublisher() {
        return new DefaultAuthenticationEventPublisher(applicationEventPublisher);
    }


//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return web -> web.ignoring().antMatchers(SecurityConstants.IGNORING_URL);
//    }

}
