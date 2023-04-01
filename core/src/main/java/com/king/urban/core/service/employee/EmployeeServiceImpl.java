package com.king.urban.core.service.employee;

import com.king.urban.common.util.StringUtils;
import com.king.urban.core.entity.employee.*;
import com.king.urban.core.mapstruct.EmployeeMapper;
import com.king.urban.core.pojo.dto.employee.CreateEmployeeDTO;
import com.king.urban.core.pojo.dto.employee.SearchEmployeeDTO;
import com.king.urban.core.pojo.dto.employee.UpdateEmployeeDTO;
import com.king.urban.core.repository.employee.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

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
    public void create(CreateEmployeeDTO employeeDTO) {
        String username = employeeDTO.getUsername();

        if (employeeRepository.existsByUsername(new Username(username))) {
            log.warn("新增账号失败 因为账号:{}已经存在", username);
        }

        Employee employee = new Employee();
        employee.updateName(new Name(employeeDTO.getName()));
        employee.updateUsername(new Username(username));
        employee.updatePassword(new Password("123456"));
        employee.updateMobilePhone("13384614120");
        employeeRepository.save(employee);
    }

    @Override
    public void update(UpdateEmployeeDTO updateEmployeeDTO) {
        employeeRepository.findById(updateEmployeeDTO.getId()).ifPresent(employee -> {
            if (StringUtils.isNotEmpty(updateEmployeeDTO.getName())) {
                employee.updateName(new Name(updateEmployeeDTO.getName()));
            }
        });
    }

}
