package com.king.urban.security.cas;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * 集成cas的配置
 *
 * @author liukai
 */
@Configuration
public class CasSecurityConfigurer {

    private final Logger log = LoggerFactory.getLogger(getClass());


    @Autowired
    private ObjectMapper objectMapper;

//    @Bean
//    public SecurityFilterChain casSecurityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .requestMatchers()
//                .antMatchers(HttpMethod.GET, CAS_LOGIN_URL, CAS_LOGOUT_URL)
//                .and()
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .logout().logoutRequestMatcher(new AntPathRequestMatcher(CAS_LOGOUT_URL, HttpMethod.GET.name())).logoutSuccessUrl(casProperties.getServerUrlPrefix() + "/logout").addLogoutHandler(new CasLogoutHandler())
//                .and()
//                .cors()
//                .and()
//                .csrf().disable()
//                .addFilterAt(new SingleSignOutFilter(), LogoutFilter.class)
//                .addFilterBefore(casAuthenticationFilter(), RequestCacheAwareFilter.class)
//                .exceptionHandling().authenticationEntryPoint(casAuthenticationEntryPoint());
//        return http.build();
//    }

}
