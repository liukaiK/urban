package com.king.urban.main.security.web.authentication.satoken;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.king.urban.common.constant.SysConstants;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class WebStpInterface implements StpInterface {

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        SaSession session = StpUtil.getSessionByLoginId(loginId);
        return new ArrayList<>((Collection<String>) session.get(SysConstants.SESSION_CURRENT_PERMISSION));
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        SaSession session = StpUtil.getSessionByLoginId(loginId);
        return new ArrayList<>((Collection<String>) session.get(SysConstants.SESSION_CURRENT_POST));
    }

}
