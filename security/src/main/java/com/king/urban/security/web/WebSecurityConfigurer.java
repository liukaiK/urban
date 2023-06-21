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
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import java.util.Arrays;

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

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.debug("----------初始化web SpringSecurity功能----------");
        http
                .authorizeRequests()
                .antMatchers(SecurityConstants.LOGIN_PROCESS_URL).permitAll()
                .anyRequest().permitAll()
                .and()
                //TODO logout这块要改
                .logout().logoutSuccessUrl(SecurityConstants.LOGIN_PROCESS_URL)
                .and()
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()
//                .sessionManagement().disable()
//                .anonymous().disable()
//                .securityContext().disable()
                .cors()
                .and()
                .addFilterBefore(usernamePasswordCaptchaAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(authorizationFilter(), FilterSecurityInterceptor.class)
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).accessDeniedHandler(accessDeniedHandler);
        return http.build();
    }


    @Autowired
    private SaFilterErrorStrategy saFilterErrorStrategy;

    @Autowired
    private SaFilterAuthStrategy saFilterAuthStrategy;

    private SaServletFilter authorizationFilter() {
        SaServletFilter saServletFilter = new SaServletFilter();
        saServletFilter.setIncludeList(Arrays.asList(SecurityConstants.AUTH_URL));
        saServletFilter.setExcludeList(Arrays.asList(SecurityConstants.IGNORING_URL));
        saServletFilter.setAuth(saFilterAuthStrategy);
        saServletFilter.setError(saFilterErrorStrategy);
        return saServletFilter;
    }

    public UsernamePasswordCaptchaAuthenticationFilter usernamePasswordCaptchaAuthenticationFilter() {
        UsernamePasswordCaptchaAuthenticationFilter usernamePasswordCaptchaAuthenticationFilter = new UsernamePasswordCaptchaAuthenticationFilter();
        usernamePasswordCaptchaAuthenticationFilter.setAuthenticationManager(new ProviderManager(Arrays.asList(userDetailsAuthenticationProvider())));
        usernamePasswordCaptchaAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        usernamePasswordCaptchaAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        usernamePasswordCaptchaAuthenticationFilter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy);
        usernamePasswordCaptchaAuthenticationFilter.setObjectMapper(objectMapper);
        return usernamePasswordCaptchaAuthenticationFilter;
    }


    public AuthenticationProvider userDetailsAuthenticationProvider() {
        UserDetailsAuthenticationProvider authenticationProvider = new UserDetailsAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
//        authenticationProvider.setRedisUtils(redisUtils);
        return authenticationProvider;
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().antMatchers(SecurityConstants.IGNORING_URL);
    }

}
