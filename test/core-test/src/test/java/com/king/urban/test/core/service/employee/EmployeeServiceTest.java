package com.king.urban.test.core.service.employee;

import com.king.urban.core.pojo.dto.employee.CreateEmployeeDTO;
import com.king.urban.core.pojo.dto.employee.UpdateEmployeeDTO;
import com.king.urban.core.service.employee.EmployeeService;
import com.king.urban.test.core.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

public class EmployeeServiceTest extends BaseTest {

    @Autowired
    private EmployeeService employeeService;


    @Test
    @Transactional
    @Rollback(value = false)
    public void employeeCreateTest() {
        CreateEmployeeDTO createEmployeeDTO = new CreateEmployeeDTO();
        createEmployeeDTO.setName("超级管理员");
        createEmployeeDTO.setUsername("admin");
        createEmployeeDTO.setTelMobile("13384514111");
        createEmployeeDTO.setPassword("password");
        createEmployeeDTO.setDeptId(1L);
        employeeService.create(createEmployeeDTO);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void employeeUpdateTest() throws Exception {
        UpdateEmployeeDTO updateEmployeeDTO = new UpdateEmployeeDTO();
        updateEmployeeDTO.setId(1L);
        updateEmployeeDTO.setMobilePhone("13333333333");
        employeeService.update(updateEmployeeDTO);

    }

}
