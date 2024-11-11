package com.king.urban.core.service.employee;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.king.urban.common.util.StringUtils;
import com.king.urban.core.converter.EmployeeConverter;
import com.king.urban.core.entity.dept.Dept;
import com.king.urban.core.entity.employee.*;
import com.king.urban.core.entity.post.Post;
import com.king.urban.core.pojo.dto.employee.CreateEmployeeDTO;
import com.king.urban.core.pojo.dto.employee.RemoveEmployeeDTO;
import com.king.urban.core.pojo.dto.employee.SearchEmployeeDTO;
import com.king.urban.core.pojo.dto.employee.UpdateEmployeeDTO;
import com.king.urban.core.pojo.vo.employee.EmployeeVO;
import com.king.urban.core.repository.dept.DeptRepository;
import com.king.urban.core.repository.employee.EmployeeRepository;
import com.king.urban.core.repository.post.PostRepository;
import com.king.urban.security.util.CurrentPrincipalUtil;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.IdentityService;
import org.flowable.idm.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@Transactional
public class DatabaseEmployeeOperations implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private PostRepository postRepository;

//    @Autowired
//    private IdentityService identityService;

    @Autowired
    private EmployeeConverter employeeConverter;

    private Specification<Employee> buildSearchSpecification(SearchEmployeeDTO searchEmployeeDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            root.fetch(Employee_.DEPT, JoinType.LEFT);
            if (StringUtils.isNotEmpty(searchEmployeeDTO.getName())) {
                predicates.add(criteriaBuilder.like(root.get(Employee_.NAME).get(Name_.NAME), "%" + searchEmployeeDTO.getName() + "%"));
            }
            if (StringUtils.isNotEmpty(searchEmployeeDTO.getUsername())) {
                predicates.add(criteriaBuilder.equal(root.get(Employee_.USERNAME), new Username(searchEmployeeDTO.getUsername())));
            }
            if (StringUtils.isNotEmpty(String.valueOf(searchEmployeeDTO.getDeptId()))) {
                predicates.add(criteriaBuilder.equal(root.get(Employee_.DEPT), new Dept(searchEmployeeDTO.getDeptId())));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

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

    @Override
    public Employee create(CreateEmployeeDTO employeeDTO) {
        Dept dept = deptRepository.findById(employeeDTO.getDeptId())
                .orElseThrow(() -> new IllegalArgumentException(StrUtil.format("非法的部门ID:{}", employeeDTO.getDeptId())));

        if (existsByUsername(new Username(employeeDTO.getUsername()))) {
            log.warn("新增账号失败 因为账号:{}已经存在", employeeDTO.getUsername());
            throw new IllegalArgumentException(StrUtil.format("新增账号失败 因为账号：{}已经存在", employeeDTO.getUsername()));
        }

        List<Post> posts = postRepository.findAllById(Convert.toList(Long.class, employeeDTO.getPostIds()));
        if (CollectionUtil.isEmpty(posts)) {
            throw new IllegalArgumentException(StrUtil.format("请设置岗位"));
        }

        Employee employee = saveToDatabase(employeeDTO, dept, posts);

        synchronizeToWorkflow(employee);

        return employee;
    }

    private Employee saveToDatabase(CreateEmployeeDTO employeeDTO, Dept dept, List<Post> posts) {
        Employee employee = new Employee();
        employee.updateDept(dept);
        employee.updatePosts(posts);
        employee.updateTelMobile(employeeDTO.getTelMobile());
        employee.updateName(new Name(employeeDTO.getName()));
        employee.updateGender(Convert.convert(Character.class, employeeDTO.getGender()));
        employee.updateUsername(new Username(employeeDTO.getUsername()));
        employee.updatePassword(new Password(employeeDTO.getPassword()));
        employee.updateCreateEmployee(CurrentPrincipalUtil.getCurrentPrincipal().convertToEmployee());
        employeeRepository.save(employee);
        return employee;
    }

    private void synchronizeToWorkflow(Employee employee) {
//        User user = identityService.newUser(String.valueOf(employee.getId()));
//        user.setDisplayName(employee.getName());
//        user.setFirstName(employee.getName());
//        user.setEmail(employee.getEmail());
//        user.setPassword(employee.getEncodedPassword());
//        identityService.saveUser(user);
    }

    @Override
    public void update(UpdateEmployeeDTO updateEmployeeDTO) {
        employeeRepository.findById(updateEmployeeDTO.getId()).ifPresent(employee -> {
            employee.updateName(new Name(updateEmployeeDTO.getName()));
            employee.updateDept(new Dept(updateEmployeeDTO.getDeptId()));
//            employee.updateGender(updateEmployeeDTO.getGender());
//            employee.updateMobilePhone(new Telephone(updateEmployeeDTO.getMobilePhone()));
        });
    }

    @Override
    public void remove(RemoveEmployeeDTO removeEmployeeDTO) {
        Long[] employeeIds = Convert.toLongArray(removeEmployeeDTO.getIds().split(","));
        if (ArrayUtil.isNotEmpty(employeeIds)) {

            for (Employee employee : employeeRepository.findAllById(Arrays.asList(employeeIds))) {
                if (employee.isSystemEmployee()) {
                    throw new IllegalArgumentException("系统人员不允许删除");
                }
            }

//            employeeRepository.softDeleteAllById(Arrays.asList(employeeIds));
        }

    }

    @Override
    public boolean existsByUsername(Username username) {
        return employeeRepository.existsByUsername(username);
    }

}
