package com.king.urban.main;

import com.king.urban.common.constant.SysConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.Set;

@EnableJpaAuditing
@EntityScan(SysConstants.BASE_PACKAGE)
@EnableJpaRepositories(basePackages = SysConstants.BASE_PACKAGE)
@SpringBootApplication(scanBasePackages = SysConstants.BASE_PACKAGE)
public class MainApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(MainApplication.class, args);

        EntityManager entityManager = configurableApplicationContext.getBean(EntityManager.class);

        Metamodel metamodel = entityManager.getMetamodel();

        Set<EntityType<?>> entities = metamodel.getEntities();



    }

}
