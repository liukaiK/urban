package com.king.urban.main.security.web.authentication.satoken;

import cn.dev33.satoken.filter.SaFilterAuthStrategy;
import cn.dev33.satoken.stp.StpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class WebSaFilterAuthStrategy implements SaFilterAuthStrategy {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void run(Object r) {
        log.debug("---------- 进入Sa-Token全局认证 -----------");
        // 登录认证 -- 拦截所有路由，并排除/user/doLogin 用于开放登录
//            SaRouter.match("/**", "/user/doLogin", () -> StpUtil.checkLogin());

        StpUtil.checkLogin();
        // 更多拦截处理方式，请参考“路由拦截式鉴权”章节 */
    }
}
