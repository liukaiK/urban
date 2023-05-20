package com.king.urban.gis.service;

import com.king.urban.gis.entity.ShpMapping;
import com.king.urban.gis.repository.ShpMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MappingServiceImpl implements MappingService {

    @Autowired
    private ShpMappingRepository shpMappingRepository;

    @Override
    public Map<String, String> findAll() {
        List<ShpMapping> componentShpMappings = shpMappingRepository.findAll();
        Map<String, String> mapping = new HashMap<>();
        for (ShpMapping componentShpMapping : componentShpMappings) {
            mapping.put(componentShpMapping.getColumnName(), componentShpMapping.getAttributeName());
        }
        return mapping;
    }


}
