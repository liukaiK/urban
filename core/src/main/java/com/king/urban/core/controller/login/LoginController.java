package com.king.urban.core.controller.login;

import cn.dev33.satoken.stp.StpUtil;
import com.king.urban.common.Result;
import com.king.urban.core.pojo.dto.login.LoginDTO;
import com.king.urban.core.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginDTO) {
        loginService.login(loginDTO);
        return Result.success(StpUtil.getTokenInfo());
    }

}
