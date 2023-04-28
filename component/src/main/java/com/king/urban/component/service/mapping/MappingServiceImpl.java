package com.king.urban.component.service.mapping;

import com.king.urban.component.entity.ComponentShpMapping;
import com.king.urban.component.repository.ComponentShpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MappingServiceImpl implements MappingService {

    @Autowired
    private ComponentShpRepository componentShpRepository;

    @Override
    public Map<String, String> findAll() {
        List<ComponentShpMapping> componentShpMappings = componentShpRepository.findAll();
        Map<String, String> mapping = new HashMap<>();
        for (ComponentShpMapping componentShpMapping : componentShpMappings) {
            mapping.put(componentShpMapping.getColumnName(), componentShpMapping.getAttributeName());
        }
        return mapping;
    }


}
