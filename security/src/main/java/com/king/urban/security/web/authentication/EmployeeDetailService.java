package com.king.urban.security.web.authentication;

import cn.hutool.core.text.StrFormatter;
import com.king.urban.core.entity.employee.Employee;
import com.king.urban.core.entity.employee.Username;
import com.king.urban.core.repository.employee.EmployeeRepository;
import com.king.urban.security.web.principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDetailService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Employee employee = employeeRepository.getByUsername(new Username(username))
                .orElseThrow(() -> new UsernameNotFoundException(StrFormatter.format("账号:{}不存在!", username)));

        return Principal.fromEmployee(employee);
    }

}
