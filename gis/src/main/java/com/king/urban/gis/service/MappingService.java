package com.king.urban.gis.service;

import com.king.urban.gis.entity.mapping.Type;

import java.util.Map;

public interface MappingService {

    Map<String, String> findAll(Type type);

}
