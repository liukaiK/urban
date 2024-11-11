package com.king.urban.grid.service.admindiv;

import com.king.urban.grid.entity.AdminDiv;
import com.king.urban.grid.entity.Level;
import com.king.urban.grid.pojo.AdminDivDTO;
import com.king.urban.grid.repository.AdminDivRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminDivServiceImpl implements AdminDivService {

    @Autowired
    private AdminDivRepository adminDivRepository;

    @Override
    public void create(AdminDivDTO adminDivDTO) {
        AdminDiv adminDiv = new AdminDiv();
        adminDiv.updateName(adminDivDTO.getName());
        adminDiv.updateCode(adminDivDTO.getCode());
        adminDiv.updateLevel(Level.get(adminDivDTO.getLevel()));
        adminDivRepository.save(adminDiv);
    }

}
