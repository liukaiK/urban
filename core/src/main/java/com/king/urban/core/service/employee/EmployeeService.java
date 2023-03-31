package com.king.urban.core.service.employee;

import com.king.urban.core.pojo.dto.employee.SaveEmployeeDTO;
import com.king.urban.core.pojo.dto.employee.SearchEmployeeDTO;
import com.king.urban.core.pojo.dto.employee.UpdateEmployeeDTO;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

    void search(SearchEmployeeDTO searchEmployeeDTO, Pageable pageable);

    void save(SaveEmployeeDTO employeeDTO);

    void update(UpdateEmployeeDTO updateEmployeeDTO);

//    void remove();

}
