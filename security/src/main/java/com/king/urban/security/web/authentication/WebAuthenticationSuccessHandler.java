package com.king.urban.security.web.authentication;

import cn.dev33.satoken.stp.StpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.king.urban.common.Result;
import com.king.urban.common.constant.SysConstants;
import com.king.urban.common.util.ResponseUtil;
import com.king.urban.security.web.principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class WebAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        Principal principal = (Principal) authentication.getPrincipal();
        StpUtil.login(principal.getId(), "web");
        StpUtil.getSession().set(SysConstants.SESSION_CURRENT_PRINCIPAL, principal);
        ResponseUtil.write(response, objectMapper.writeValueAsString(Result.success(StpUtil.getTokenInfo())), MediaType.APPLICATION_JSON_VALUE);
    }

}
