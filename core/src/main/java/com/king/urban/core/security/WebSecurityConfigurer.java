//package com.king.urban.core.security;
//
//import com.king.urban.common.constant.SecurityConstants;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
///**
// * @author liukai
// */
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfigurer {
//
//
//    @Bean
//    public SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
//
//        http
//                .authorizeRequests()
//                .antMatchers(SecurityConstants.LOGIN_PROCESS_URL).permitAll()
//                .anyRequest().authenticated()
//                .and()
////                .logout().logoutSuccessHandler(SystemConstant.PC_LOGIN_PAGE)
////                .and()
//                .headers().frameOptions().sameOrigin()
//                .and()
//                .csrf().disable();
////                .addFilterBefore(usernamePasswordCaptchaAuthenticationFilter(), RequestCacheAwareFilter.class)
////                .addFilterBefore(new XssFilter(), WebAsyncManagerIntegrationFilter.class)
////                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
//
//
//        return http.build();
//    }
//
//
//}
