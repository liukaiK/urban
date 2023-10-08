package com.king.urban.core.jpa;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.resource.jdbc.spi.StatementInspector;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 * 数据权限的拦截器 以部门为纬度
 *
 * @author liukai
 */
@Slf4j
public class JpaStatementInspector implements StatementInspector, ServletContainerInitializer {

    @Override
    public String inspect(String sql) {
//        System.out.println(sql);
//        log.info(sql);
        return sql;
    }

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        log.info("初始化");
    }


}
