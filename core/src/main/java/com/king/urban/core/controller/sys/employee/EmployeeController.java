package com.king.urban.core.controller.sys.employee;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.king.urban.common.Result;
import com.king.urban.core.pojo.dto.employee.CreateEmployeeDTO;
import com.king.urban.core.pojo.dto.employee.RemoveEmployeeDTO;
import com.king.urban.core.pojo.dto.employee.SearchEmployeeDTO;
import com.king.urban.core.pojo.dto.employee.UpdateEmployeeDTO;
import com.king.urban.core.pojo.vo.employee.EmployeeVO;
import com.king.urban.core.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

/**
 * 人员控制器
 *
 * @author liukai
 */
@RestController
@RequestMapping("/system/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/search")
    @SaCheckPermission("system:employee:search")
    public Result search(SearchEmployeeDTO searchEmployeeDTO, @PageableDefault Pageable pageable) {
        Page<EmployeeVO> page = employeeService.search(searchEmployeeDTO, pageable);
        return Result.success(page);
    }

    @PostMapping("/create")
    public Result create(@RequestBody CreateEmployeeDTO createEmployeeDTO) {
        employeeService.create(createEmployeeDTO);
        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody UpdateEmployeeDTO updateEmployeeDTO) {
        employeeService.update(updateEmployeeDTO);
        return Result.success();
    }

    @PostMapping("/remove")
    public Result remove(@RequestBody RemoveEmployeeDTO removeEmployeeDTO) {
        employeeService.remove(removeEmployeeDTO);
        return Result.success();
    }

}
