package com.king.urban.security.util;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.Convert;
import com.king.urban.common.constant.SysConstants;
import com.king.urban.security.web.principal.Principal;

import java.util.HashMap;

public class CurrentPrincipalUtil {

    /**
     * 获取当前登录人的信息
     */
    public static Principal getCurrentPrincipal() {
        HashMap<String, Object> hashMap = (HashMap<String, Object>) StpUtil.getSession().get(SysConstants.SESSION_CURRENT_PRINCIPAL);
        Principal principal = new Principal();
        principal.setId(Convert.convert(Long.class, hashMap.get("id")));
//        principal.setName(hashMap.get("name"));
//        principal.setUsername(hashMap.get("username"));
//        principal.setDeptId(hashMap.get("deptId"));
//        principal.setDeptName(hashMap.get("deptName"));
        return principal;
    }


}
