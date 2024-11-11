package com.king.urban.gis.repository;

import com.king.urban.common.repository.BaseRepository;
import com.king.urban.common.repository.DeletableRepository;
import com.king.urban.gis.mapping.ShpMapping;
import com.king.urban.gis.mapping.Type;

import java.util.List;

public interface ShpMappingRepository extends BaseRepository<ShpMapping, Long>, DeletableRepository<ShpMapping, Long> {

    List<ShpMapping> findByType(Type type);

}
