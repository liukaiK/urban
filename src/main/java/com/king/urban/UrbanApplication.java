package com.king.urban;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class UrbanApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrbanApplication.class, args);
    }

}
