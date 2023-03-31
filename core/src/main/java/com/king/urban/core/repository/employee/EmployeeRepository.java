package com.king.urban.core.repository.employee;

import com.king.urban.core.entity.employee.Employee;
import com.king.urban.core.entity.employee.Username;
import com.king.urban.core.repository.BaseRepository;
import com.king.urban.core.repository.DeletableRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends BaseRepository<Employee, Long>, DeletableRepository<Employee, Long> {

    Optional<Employee> getByUsername(Username username);

}
