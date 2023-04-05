package com.king.urban.core.controller.dept;

import com.king.urban.core.pojo.dto.dept.CreateDeptDTO;
import com.king.urban.core.service.dept.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 部门控制器
 *
 * @author liukai
 */
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

//    @GetMapping("/seaarch")
//    public Result search(SearchEmployeeDTO searchEmployeeDTO, @PageableDefault Pageable pageable) {
//        employeeService.search(searchEmployeeDTO, pageable);
//        return null;
//    }

    @PostMapping("/create")
    public void create(@RequestBody CreateDeptDTO createDeptDTO) {
        deptService.create(createDeptDTO);
    }

//    @PostMapping("/update")
//    public void update(@RequestBody UpdateEmployeeDTO updateEmployeeDTO) {
//        employeeService.update(updateEmployeeDTO);
//    }


}
