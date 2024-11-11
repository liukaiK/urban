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
        HashMap<String, Object> principalMap = (HashMap<String, Object>) StpUtil.getSession().get(SysConstants.SESSION_CURRENT_PRINCIPAL);
        Principal principal = new Principal();
        principal.setId(Convert.convert(Long.class, principalMap.get(SysConstants.SESSION_CURRENT_EMPLOYEE_ID)));
        principal.setName(Convert.convert(String.class, principalMap.get(SysConstants.SESSION_CURRENT_EMPLOYEE_NAME)));
        principal.setUsername(Convert.convert(String.class, principalMap.get(SysConstants.SESSION_CURRENT_USERNAME)));
        principal.setDeptId(Convert.convert(Long.class, principalMap.get(SysConstants.SESSION_CURRENT_DEPT_ID)));
        principal.setDeptName(Convert.convert(String.class, principalMap.get(SysConstants.SESSION_CURRENT_DEPT_NAME)));
        principal.setChildrenDeptIds(Convert.toList(Long.class, principalMap.get(SysConstants.SESSION_CURRENT_CHILDREN_DEPT_ID)));
        //TODO 权限还没有完成
        return principal;
    }


}
