package com.king.urban.component.service.component;

import com.king.urban.component.entity.component.Component;
import com.king.urban.component.repository.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComponentServiceImpl implements ComponentService {

    @Autowired
    private ComponentRepository componentRepository;


    @Override
    public void create(Component component) {

    }

}
