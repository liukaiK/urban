package com.king.urban.main.core.service.employee;

import com.king.urban.main.core.entity.employee.Username;
import com.king.urban.main.core.pojo.dto.employee.CreateEmployeeDTO;
import com.king.urban.main.core.pojo.dto.employee.RemoveEmployeeDTO;
import com.king.urban.main.core.pojo.dto.employee.SearchEmployeeDTO;
import com.king.urban.main.core.pojo.dto.employee.UpdateEmployeeDTO;
import com.king.urban.main.core.pojo.vo.employee.EmployeeVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Collection;

public interface EmployeeService {

    Page<EmployeeVO> search(SearchEmployeeDTO searchEmployeeDTO, Pageable pageable);

    Collection<EmployeeVO> search(SearchEmployeeDTO searchEmployeeDTO, Sort sort);

    void create(CreateEmployeeDTO employeeDTO);

    void update(UpdateEmployeeDTO updateEmployeeDTO);

    void remove(RemoveEmployeeDTO removeEmployeeDTO);

    boolean existsByUsername(Username username);

//    void remove();

}
