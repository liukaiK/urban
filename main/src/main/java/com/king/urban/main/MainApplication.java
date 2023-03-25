package com.king.urban.main;

import com.king.urban.common.constant.SysConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = SysConstants.BASE_PACKAGE)
public class Urban {

    public static void main(String[] args) {
        SpringApplication.run(Urban.class, args);
    }

}
