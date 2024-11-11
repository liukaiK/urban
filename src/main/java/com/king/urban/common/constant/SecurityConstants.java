package com.king.urban.common.constant;

/**
 * @author liukai
 */
public class SecurityConstants {

    /**
     * 不拦截的资源
     */
    public final static String[] IGNORING_URL = new String[]{
            "/favicon.ico",
            SecurityConstants.SYSTEM_INIT_URL
    };

    /**
     * 系统初始化的url
     */
    public final static String SYSTEM_INIT_URL = "/system/init";

    /**
     * 需要拦截的资源
     */
    public final static String AUTH_URL = "/**";

    public final static String LOGIN_PROCESS_URL = "/login";

    public final static String LOGOUT_PROCESS_URL = "/logout";

}
