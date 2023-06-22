package com.king.urban.common.constant;

public class SysConstants {

    public static final String BASE_PACKAGE = "com.king.urban";

    public static final String SNOW_CLASS = BASE_PACKAGE + ".common.id.SnowIdGenerator";

    public static final String SNOW_ID = "snow_id";

    public static final String DELETED_FILED = "deleted";

    public static final int DELETED_VALUE = 0;

    public static final String WHERE_DELETE = DELETED_FILED + "=" + DELETED_VALUE;

    /**
     * 当前登录人的主体信息
     */
    public static final String SESSION_CURRENT_PRINCIPAL = "current_principal";

    /**
     * 当前登录人的岗位
     */
    public static final String SESSION_CURRENT_POST = "current_post";

    /**
     * 当前登录人的权限标识
     */
    public static final String SESSION_CURRENT_PERMISSION = "current_permission";

}
