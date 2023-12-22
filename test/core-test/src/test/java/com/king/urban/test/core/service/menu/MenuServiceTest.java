package com.king.urban.test.core.service.menu;

import com.king.urban.main.core.pojo.dto.menu.CreateMenuDTO;
import com.king.urban.main.core.service.menu.MenuService;
import com.king.urban.test.core.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MenuServiceTest extends BaseTest {

    @Autowired
    private MenuService menuService;

    @Test
    public void createTest() {
        CreateMenuDTO createMenuDTO = new CreateMenuDTO();

        createMenuDTO.setName("部门管理");
        createMenuDTO.setPermission("system:dept");

        menuService.create(createMenuDTO);
    }

}
