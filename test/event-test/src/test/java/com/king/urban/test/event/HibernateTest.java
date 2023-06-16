package com.king.urban.test.event;

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import javax.persistence.EntityManager;

public class HibernateTest {

    @Test
    public void init() {

        Configuration configuration = new Configuration();

        // 设置数据库连接等属性
        configuration.setProperty(AvailableSettings.URL, "jdbc:mysql://127.0.0.1:3306/urban?useUnicode=true&autoReconnect=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8&nullCatalogMeansCurrent=true&allowPublicKeyRetrieval=true");
        configuration.setProperty(AvailableSettings.USER, "root");
        configuration.setProperty(AvailableSettings.PASS, "Root@123");
        configuration.setProperty(AvailableSettings.DRIVER, "com.mysql.cj.jdbc.Driver");

        EntityManager entityManager = configuration
                .buildSessionFactory()
                .createEntityManager();


//        configuration.addAnnotatedClass(YourEntity.class);

    }


}
