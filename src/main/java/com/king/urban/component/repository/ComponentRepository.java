package com.king.urban.component.repository;

import com.king.urban.common.repository.BaseRepository;
import com.king.urban.common.repository.DeletableRepository;
import com.king.urban.component.component.Component;

public interface ComponentRepository extends BaseRepository<Component, Long>, DeletableRepository<Component, Long> {
}
