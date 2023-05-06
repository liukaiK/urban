package com.king.urban.core.security.principal;

import com.king.urban.core.entity.employee.Employee;
import lombok.Data;

@Data
public class Principal {

    private Long id;

    private String name;

    private String username;

    private String deptName;

    private Long deptId;

    private void principal() {

    }

    public static Principal fromEmployee(Employee employee) {
        Principal principal = new Principal();
        principal.setId(employee.getId());
        principal.setName(employee.getName());
        principal.setUsername(employee.getUsername());
        principal.setDeptId(employee.getDept().getId());
        principal.setDeptName(employee.getDept().getName());
        return principal;
    }

}
