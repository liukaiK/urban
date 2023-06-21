package com.king.urban.security.web.authentication;

import cn.dev33.satoken.stp.StpUtil;
import com.king.urban.common.constant.SysConstants;
import com.king.urban.security.web.principal.Principal;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用来存储当前登录人的信息
 *
 * @author liukai
 */
@Component
public class WebSessionAuthenticationStrategy implements SessionAuthenticationStrategy {

    @Override
    public void onAuthentication(Authentication authentication, HttpServletRequest request, HttpServletResponse response) throws SessionAuthenticationException {
        Principal principal = (Principal) authentication.getPrincipal();
        StpUtil.login(principal.getId(), "web");
        StpUtil.getSession().set(SysConstants.SESSION_CURRENT_PRINCIPAL, principal);
        StpUtil.getSession().set(SysConstants.SESSION_CURRENT_PERMISSION, principal.getAuthorities());
    }


}
