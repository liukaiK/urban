package com.king.urban.main.security.web.authentication.satoken;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.filter.SaFilterErrorStrategy;
import com.king.urban.main.security.exception.TokenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

/**
 * 出现异常要throw出来 交给SpringSecurity去处理
 *
 * @author liukai
 */
@Component
public class WebSaFilterErrorStrategy implements SaFilterErrorStrategy {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public Object run(Throwable e) {
        log.debug("saServletFilter出现异常", e);

        if (e instanceof NotLoginException) {
            throw new TokenException(e.getMessage());
        }


        throw new AccessDeniedException("123123123123");

//        return null;
    }

}
