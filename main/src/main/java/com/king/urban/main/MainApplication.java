package com.king.urban.main;

import com.king.urban.common.constant.SysConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EntityScan(SysConstants.BASE_PACKAGE)
@EnableJpaRepositories(basePackages = SysConstants.BASE_PACKAGE)
@SpringBootApplication(scanBasePackages = SysConstants.BASE_PACKAGE)
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}
