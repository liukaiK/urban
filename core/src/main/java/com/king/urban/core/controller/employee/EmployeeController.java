package com.king.urban.core.controller.employee;

import com.king.urban.common.Result;
import com.king.urban.core.pojo.dto.employee.CreateEmployeeDTO;
import com.king.urban.core.pojo.dto.employee.SearchEmployeeDTO;
import com.king.urban.core.pojo.dto.employee.UpdateEmployeeDTO;
import com.king.urban.core.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

/**
 * 人员控制器
 *
 * @author liukai
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/seaarch")
    public Result search(SearchEmployeeDTO searchEmployeeDTO, @PageableDefault Pageable pageable) {
        employeeService.search(searchEmployeeDTO, pageable);
        return null;
    }

    @PostMapping("/create")
    public void create(@RequestBody CreateEmployeeDTO createEmployeeDTO) {
        employeeService.create(createEmployeeDTO);
    }

    @PostMapping("/update")
    public void update(@RequestBody UpdateEmployeeDTO updateEmployeeDTO) {
        employeeService.update(updateEmployeeDTO);
    }


}
