package com.king.urban.main.gis.service;

import com.king.urban.main.gis.mapping.Type;

import java.util.Map;

public interface MappingService {

    Map<String, String> findAll(Type type);

}
