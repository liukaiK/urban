package com.king.urban.security.web.authentication.satoken;

import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.interceptor.SaInterceptor;
import com.king.urban.common.constant.SecurityConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，打开注解式鉴权功能
        registry.addInterceptor(new SaInterceptor()).addPathPatterns(SecurityConstants.AUTH_URL);
    }

    @Bean
    @Primary
    public SaTokenConfig getSaTokenConfigPrimary() {
        SaTokenConfig config = new SaTokenConfig();
        config.setTokenName("token");
        config.setTimeout(5 * 12 * 30 * 24 * 60 * 60);
        config.setActivityTimeout(30 * 24 * 60 * 60);
        config.setIsConcurrent(true);
        config.setIsShare(true);
        config.setTokenStyle("simple-uuid");
        config.setIsLog(false);
        return config;
    }


}
