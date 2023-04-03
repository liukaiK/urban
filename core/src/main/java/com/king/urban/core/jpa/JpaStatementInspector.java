package com.king.urban.core.jpa;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.resource.jdbc.spi.StatementInspector;

/**
 * 数据权限的拦截器 以部门为纬度
 *
 * @author liukai
 */
@Slf4j
public class JpaStatementInspector implements StatementInspector {

    @Override
    public String inspect(String sql) {
        System.out.println(sql);
        log.info(sql);
        return sql;
    }

}
