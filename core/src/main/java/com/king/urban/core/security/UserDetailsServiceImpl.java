package com.king.urban.core.security;

import com.king.urban.core.entity.employee.Employee;
import com.king.urban.core.entity.employee.Username;
import com.king.urban.core.repository.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Employee employee = employeeRepository.getByUsername(new Username(username))
                .orElseThrow(() -> new UsernameNotFoundException("账号:{}不存在"));

        return User.builder()
                .username(employee.getUsername())
                .password(employee.getEncodedPassword())
                .authorities("admin")
                .build();
    }


}
