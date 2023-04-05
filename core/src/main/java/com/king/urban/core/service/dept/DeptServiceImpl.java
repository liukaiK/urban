package com.king.urban.core.service.dept;

import com.king.urban.core.entity.dept.Dept;
import com.king.urban.core.pojo.dto.dept.CreateDeptDTO;
import com.king.urban.core.repository.dept.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptRepository deptRepository;

    @Override
    public void create(CreateDeptDTO createDeptDTO) {
        Dept dept = new Dept();

        dept.updateName(createDeptDTO.getName());
        deptRepository.save(dept);
    }


}
