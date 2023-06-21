package com.king.urban.security.web.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.king.urban.common.Result;
import com.king.urban.common.util.ResponseUtil;
import com.king.urban.security.exception.TokenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class WebAccessDeniedHandler implements AccessDeniedHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.debug("认证出现异常", accessDeniedException);
        if (accessDeniedException instanceof TokenException) {
            // TODO 需要标准化响应码
            ResponseUtil.write(response, objectMapper.writeValueAsString(Result.fail("", accessDeniedException.getMessage())), MediaType.APPLICATION_JSON_VALUE);
        }
    }

}
