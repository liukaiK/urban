package com.king.urban.component.repository;

import com.king.urban.common.repository.BaseRepository;
import com.king.urban.common.repository.DeletableRepository;
import com.king.urban.component.entity.ComponentShpMapping;

public interface ComponentShpRepository extends BaseRepository<ComponentShpMapping, Long>, DeletableRepository<ComponentShpMapping, Long> {
}
