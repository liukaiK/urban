package com.king.component.repository;

import com.king.component.entity.ComponentShpMapping;
import com.king.urban.common.repository.BaseRepository;
import com.king.urban.common.repository.DeletableRepository;

public interface ComponentShpRepository extends BaseRepository<ComponentShpMapping, Long>, DeletableRepository<ComponentShpMapping, Long> {
}
