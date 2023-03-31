package com.king.urban.core.service.employee;

import com.king.urban.core.entity.employee.*;
import com.king.urban.core.pojo.dto.employee.SaveEmployeeDTO;
import com.king.urban.core.pojo.dto.employee.SearchEmployeeDTO;
import com.king.urban.core.pojo.dto.employee.UpdateEmployeeDTO;
import com.king.urban.core.repository.employee.EmployeeRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void search(SearchEmployeeDTO searchEmployeeDTO, Pageable pageable) {
        Specification<Employee> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotEmpty(searchEmployeeDTO.getName())) {
                predicates.add(criteriaBuilder.like(root.get(Employee_.name).get(Name_.name), "%" + searchEmployeeDTO.getName() + "%"));
            }
            if (StringUtils.isNotEmpty(searchEmployeeDTO.getUsername())) {
                predicates.add(criteriaBuilder.equal(root.get(Employee_.username), new Username(searchEmployeeDTO.getUsername())));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        Page<Employee> page = employeeRepository.findAll(specification, pageable);
//        List<UserVO> userVOList = UserMapper.INSTANCE.convert(page);
//        return new PageImpl<>(userVOList, page.getPageable(), page.getTotalElements());
    }

    @Override
    public void save(SaveEmployeeDTO employeeDTO) {

        Employee employee = new Employee();


        employeeRepository.save(employee);
    }

    @Override
    public void update(UpdateEmployeeDTO updateEmployeeDTO) {
        employeeRepository.findById(updateEmployeeDTO.getId()).ifPresent(employee -> {
            employee.updateName(new Name(updateEmployeeDTO.getName()));
        });
    }

}
