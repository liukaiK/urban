package com.king.urban.test.grid.service;

import cn.hutool.core.util.IdUtil;
import com.king.urban.grid.pojo.AdminDivDTO;
import com.king.urban.grid.service.admindiv.AdminDivService;
import com.king.urban.test.grid.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminDivServiceTest extends BaseTest {

    @Autowired
    private AdminDivService adminDivService;

    @Test
    public void adminDivCreateTest() {
        AdminDivDTO adminDivDTO = new AdminDivDTO();
        adminDivDTO.setName("XXX市");
        adminDivDTO.setLevel(0);
        adminDivDTO.setCode(IdUtil.fastSimpleUUID());
        adminDivService.create(adminDivDTO);


    }

}
