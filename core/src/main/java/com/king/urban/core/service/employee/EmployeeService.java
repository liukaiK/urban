package com.king.urban.core.service.employee;

import com.king.urban.core.entity.employee.Username;
import com.king.urban.core.pojo.dto.employee.CreateEmployeeDTO;
import com.king.urban.core.pojo.dto.employee.SearchEmployeeDTO;
import com.king.urban.core.pojo.dto.employee.UpdateEmployeeDTO;
import com.king.urban.core.pojo.vo.employee.EmployeeVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

    Page<EmployeeVO> search(SearchEmployeeDTO searchEmployeeDTO, Pageable pageable);

    void create(CreateEmployeeDTO employeeDTO);

    void update(UpdateEmployeeDTO updateEmployeeDTO);

    void remove(String ids);

    boolean existsByUsername(Username username);

//    void remove();

}
