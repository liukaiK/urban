package com.king.urban.core.controller.sys.employee;

import cn.dev33.satoken.stp.StpUtil;
import com.king.urban.common.Result;
import com.king.urban.common.constant.SysConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liukai
 */
@RestController
@RequestMapping("/employee")
public class EmployeeInfoController {


    @GetMapping("/info")
    public Result info() {
        return Result.success(StpUtil.getSession().get(SysConstants.SESSION_CURRENT_PRINCIPAL));
    }

}
