package com.king.component.repository;

import com.king.component.entity.component.Component;
import com.king.urban.common.repository.BaseRepository;
import com.king.urban.common.repository.DeletableRepository;

public interface ComponentRepository extends BaseRepository<Component, Long>, DeletableRepository<Component, Long> {
}
