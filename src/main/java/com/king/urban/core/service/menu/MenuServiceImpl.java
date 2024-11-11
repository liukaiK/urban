package com.king.urban.core.service.menu;

import com.king.urban.core.entity.menu.Menu;
import com.king.urban.core.pojo.dto.menu.CreateMenuDTO;
import com.king.urban.core.repository.menu.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public void create(CreateMenuDTO createMenuDTO) {
        Menu menu = new Menu();
        menu.updateName(createMenuDTO.getName());
        menu.updatePermission(createMenuDTO.getPermission());
        menuRepository.save(menu);
    }

}
