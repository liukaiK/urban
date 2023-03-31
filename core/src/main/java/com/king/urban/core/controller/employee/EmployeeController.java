package com.king.urban.core.controller.employee;

import com.king.urban.common.Result;
import com.king.urban.core.pojo.dto.employee.SaveEmployeeDTO;
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

    @GetMapping
    public Result search(@PageableDefault Pageable pageable) {

    }

    @PostMapping("/")
    public void save(@RequestBody SaveEmployeeDTO saveEmployeeDTO) {
        employeeService.save(saveEmployeeDTO);
    }

}
