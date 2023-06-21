package com.king.urban.security.web.authentication.satoken;

import cn.dev33.satoken.filter.SaFilterErrorStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Component
public class WebSaFilterErrorStrategy implements SaFilterErrorStrategy {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public Object run(Throwable e) {
        log.error("saServletFilter出现异常", e);
        throw new AccessDeniedException("123123123123");

//        return null;
    }

}
