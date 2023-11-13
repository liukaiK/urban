package com.king.urban.core.service.employee;

import cn.hutool.core.convert.Convert;
import com.king.urban.common.util.StringUtils;
import com.king.urban.core.converter.EmployeeConverter;
import com.king.urban.core.entity.dept.Dept;
import com.king.urban.core.entity.employee.*;
import com.king.urban.core.pojo.dto.employee.CreateEmployeeDTO;
import com.king.urban.core.pojo.dto.employee.RemoveEmployeeDTO;
import com.king.urban.core.pojo.dto.employee.SearchEmployeeDTO;
import com.king.urban.core.pojo.dto.employee.UpdateEmployeeDTO;
import com.king.urban.core.pojo.vo.employee.EmployeeVO;
import com.king.urban.core.repository.employee.EmployeeRepository;
import com.king.urban.core.repository.post.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private EmployeeConverter employeeConverter;

    @Override
    public Page<EmployeeVO> search(SearchEmployeeDTO searchEmployeeDTO, Pageable pageable) {
        Specification<Employee> specification = buildSearchSpecification(searchEmployeeDTO);
        Page<Employee> queryResult = employeeRepository.findAll(specification, pageable);
        return employeeConverter.convertPage(queryResult);
    }

    @Override
    public Collection<EmployeeVO> search(SearchEmployeeDTO searchEmployeeDTO, Sort sort) {
        Specification<Employee> specification = buildSearchSpecification(searchEmployeeDTO);
        List<Employee> employees = employeeRepository.findAll(specification, sort);
        return employeeConverter.convert(employees);
    }

    private static Specification<Employee> buildSearchSpecification(SearchEmployeeDTO searchEmployeeDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            root.fetch(Employee_.dept, JoinType.LEFT);
            if (StringUtils.isNotEmpty(searchEmployeeDTO.getName())) {
                predicates.add(criteriaBuilder.like(root.get(Employee_.name).get(Name_.name), "%" + searchEmployeeDTO.getName() + "%"));
            }
            if (StringUtils.isNotEmpty(searchEmployeeDTO.getUsername())) {
                predicates.add(criteriaBuilder.equal(root.get(Employee_.username), new Username(searchEmployeeDTO.getUsername())));
            }
            if (StringUtils.isNotEmpty(String.valueOf(searchEmployeeDTO.getDeptId()))) {
                predicates.add(criteriaBuilder.equal(root.get(Employee_.dept), new Dept(searchEmployeeDTO.getDeptId())));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Override
    public void create(CreateEmployeeDTO employeeDTO) {
        Statistics statistics = sessionFactory.getStatistics();


        String username = employeeDTO.getUsername();

        if (existsByUsername(new Username(username))) {
            log.warn("新增账号失败 因为账号:{}已经存在", username);
        }

        Employee employee = new Employee();
        employee.updateName(new Name(employeeDTO.getName()));
        employee.updateUsername(new Username(username));
        employee.updatePassword(new Password(employeeDTO.getPassword()));
        employee.updateMobilePhone(new MobilePhone(employeeDTO.getMobilePhone()));
        employee.updateDept(new Dept(employeeDTO.getDeptId()));
        employee.updatePosts(postRepository.findAllById(Arrays.asList(Convert.toLongArray(employeeDTO.getPostIds().split(",")))));
        employeeRepository.save(employee);
    }

    @Override
    public void update(UpdateEmployeeDTO updateEmployeeDTO) {
        employeeRepository.findById(updateEmployeeDTO.getId()).ifPresent(employee -> {
            employee.updateName(new Name(updateEmployeeDTO.getName()));
            employee.updateDept(new Dept(updateEmployeeDTO.getDeptId()));
            employee.updateMobilePhone(new MobilePhone(updateEmployeeDTO.getMobilePhone()));
        });
    }

    @Override
    public void remove(RemoveEmployeeDTO removeEmployeeDTO) {
        for (Long employeeId : Convert.toLongArray(removeEmployeeDTO.getIds().split(","))) {
            if (Employee.adminId.equals(employeeId)) {
                throw new IllegalArgumentException("超级管理员不允许删除");
            }
        }

        employeeRepository.softDeleteAllById(Arrays.asList(Convert.toLongArray(removeEmployeeDTO.getIds().split(","))));
    }

    @Override
    public boolean existsByUsername(Username username) {
        return employeeRepository.existsByUsername(username);
    }

}
