package com.king.urban.core.service.dept;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.king.urban.common.util.StringUtils;
import com.king.urban.core.converter.DeptConverter;
import com.king.urban.core.entity.dept.Dept;
import com.king.urban.core.entity.dept.Dept_;
import com.king.urban.core.entity.employee.Employee;
import com.king.urban.core.entity.post.Post;
import com.king.urban.core.pojo.dto.dept.CreateDeptDTO;
import com.king.urban.core.pojo.dto.dept.RemoveDeptDTO;
import com.king.urban.core.pojo.dto.dept.SearchDeptDTO;
import com.king.urban.core.pojo.vo.dept.DeptVO;
import com.king.urban.core.repository.dept.DeptRepository;
import com.king.urban.core.repository.employee.EmployeeRepository;
import com.king.urban.core.repository.post.PostRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private DeptConverter deptConverter;

    @Override
    public Page<DeptVO> search(SearchDeptDTO searchDeptDTO, Pageable pageable) {
        Specification<Dept> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotEmpty(searchDeptDTO.getName())) {
                predicates.add(criteriaBuilder.like(root.get(Dept_.NAME), "%" + searchDeptDTO.getName() + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        Page<Dept> queryResult = deptRepository.findAll(specification, pageable);
        return deptConverter.convertPage(queryResult);
    }

    @Override
    public Dept create(CreateDeptDTO createDeptDTO) {
        Dept dept = new Dept();
        if (ObjectUtil.isNotEmpty(createDeptDTO.getParentId())) {
            Dept parentDept = deptRepository.findById(createDeptDTO.getParentId()).orElseThrow(() -> new IllegalArgumentException("父部门不存在"));
            dept.updateParent(parentDept);
        }
        dept.updateName(createDeptDTO.getName());
        return deptRepository.save(dept);
    }

    @Override
    public void remove(RemoveDeptDTO removeDeptDTO) {
        List<Long> ids = Convert.toList(Long.class, removeDeptDTO.getIds());
        // 查询出要删除的部门
        List<Dept> depts = deptRepository.findAllById(ids);
        for (Dept dept : depts) {
            if (dept.isSystemDept()) {
                throw new IllegalArgumentException("系统内置部门不可以删除");
            }
            if (dept.hasChildren()) {
                throw new IllegalArgumentException("部门下面有子部门 不能删除");
            }
        }
        // 查询部门下面有没有人员
        Collection<Employee> employees = employeeRepository.findByDeptIn(depts);
        if (CollectionUtil.isNotEmpty(employees)) {
            throw new IllegalArgumentException("部门下有人员 不能删除 请先解绑之后再删除");
        }
        // 查询部门下面有没有岗位
        Collection<Post> posts = postRepository.findByDeptIn(depts);
        if (CollectionUtil.isNotEmpty(posts)) {
            throw new IllegalArgumentException("部门下有岗位 不能删除 请先解绑之后再删除");
        }
//        deptRepository.softDeleteAllById(ids);
    }


}
