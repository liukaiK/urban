package com.king.component.service.component;

import com.king.component.entity.component.Component;
import com.king.component.repository.ComponentRepository;
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
