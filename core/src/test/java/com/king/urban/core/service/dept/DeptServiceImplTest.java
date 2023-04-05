package com.king.urban.core.service.dept;

import com.king.urban.core.BaseTest;
import com.king.urban.core.pojo.dto.dept.CreateDeptDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DeptServiceImplTest extends BaseTest {

    @Autowired
    private DeptService deptService;

    @Test
    public void create() {
        CreateDeptDTO createDeptDTO = new CreateDeptDTO();
        createDeptDTO.setName("指挥大厅");
        deptService.create(createDeptDTO);

    }
}