package com.king.urban.gis.repository;

import com.king.urban.common.repository.BaseRepository;
import com.king.urban.common.repository.DeletableRepository;
import com.king.urban.gis.entity.ShpMapping;

public interface ShpMappingRepository extends BaseRepository<ShpMapping, Long>, DeletableRepository<ShpMapping, Long> {
}
