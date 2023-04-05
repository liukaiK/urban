package com.king.urban.core.service.employee;

import com.king.urban.core.entity.employee.Username;
import com.king.urban.core.pojo.dto.employee.CreateEmployeeDTO;
import com.king.urban.core.pojo.dto.employee.SearchEmployeeDTO;
import com.king.urban.core.pojo.dto.employee.UpdateEmployeeDTO;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

    void search(SearchEmployeeDTO searchEmployeeDTO, Pageable pageable);

    void create(CreateEmployeeDTO employeeDTO);

    void update(UpdateEmployeeDTO updateEmployeeDTO);

    boolean existsByUsername(Username username);

//    void remove();

}
