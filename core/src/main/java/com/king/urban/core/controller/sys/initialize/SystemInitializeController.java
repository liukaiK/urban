package com.king.urban.core.controller.sys.initialize;

import com.king.urban.common.Result;
import com.king.urban.common.constant.SecurityConstants;
import com.king.urban.core.repository.system.SystemConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SystemInitializeController {

    @Autowired
    private SystemConfigRepository systemConfigRepository;


    /**
     * 系统初始化
     */
    @GetMapping(SecurityConstants.SYSTEM_INIT_URL)
    public Result init() {
        Map<String, String> map = new HashMap<>();

        map.put("application_name", systemConfigRepository.getByName("applicationName").getValue());

        return Result.success(map);
    }

}
