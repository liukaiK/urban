package com.king.urban.core.service.login;

import cn.dev33.satoken.stp.StpUtil;
import com.king.urban.core.entity.employee.Employee;
import com.king.urban.core.entity.employee.Username;
import com.king.urban.core.pojo.dto.login.LoginDTO;
import com.king.urban.core.repository.employee.EmployeeRepository;
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
        Employee employee = employeeRepository.getByUsername(new Username(loginDTO.getUsername()))
                .orElseThrow(() -> new RuntimeException("账号不存在"));

        String rawPassword = loginDTO.getPassword();
        if (!passwordEncoder.matches(rawPassword, employee.getEncodedPassword())) {
            throw new RuntimeException("密码错误");
        }
        StpUtil.login(employee.getId(), "PC");
    }
}
