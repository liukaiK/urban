package com.king.urban.core.service.login;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.text.StrFormatter;
import com.king.urban.common.constant.SysConstants;
import com.king.urban.core.entity.employee.Employee;
import com.king.urban.core.entity.employee.Username;
import com.king.urban.core.pojo.dto.login.LoginDTO;
import com.king.urban.core.repository.employee.EmployeeRepository;
import com.king.urban.core.security.principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginServiceImpl implements LoginService {


    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void login(LoginDTO loginDTO) {
        Username username = new Username(loginDTO.getUsername());
        Employee employee = employeeRepository.getByUsername(username)
                .orElseThrow(() -> new RuntimeException(StrFormatter.format("账号:{}不存在!", username)));

        // TODO 校验密码 开发阶段 暂时先注掉
        String rawPassword = loginDTO.getPassword();
//        if (!passwordEncoder.matches(rawPassword, employee.getEncodedPassword())) {
//            throw new RuntimeException("密码错误");
//        }
        StpUtil.login(employee.getId(), "PC");
        StpUtil.getSession().set(SysConstants.SESSION_CURRENT_PRINCIPAL, Principal.fromEmployee(employee));
    }
}
