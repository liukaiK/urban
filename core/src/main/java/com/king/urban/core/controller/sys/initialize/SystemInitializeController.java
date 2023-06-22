package com.king.urban.core.controller.sys.initialize;

import com.king.urban.common.Result;
import com.king.urban.core.entity.system.SystemConfig;
import com.king.urban.core.repository.system.SystemConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system")
public class SystemInitializeController {

    @Autowired
    private SystemConfigRepository systemConfigRepository;


    /**
     * 系统初始化
     */
    @GetMapping("/init")
    public Result init() {
        Map<String, String> map = new HashMap<>();
        List<SystemConfig> systemConfigs = systemConfigRepository.findAll();

        for (SystemConfig systemConfig : systemConfigs) {
            map.put("application_name", systemConfig.getValue());
        }

        return Result.success(map);
    }

}
