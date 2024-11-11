package com.king.urban.grid.repository;

import com.king.urban.common.repository.BaseRepository;
import com.king.urban.common.repository.DeletableRepository;
import com.king.urban.grid.entity.DutyGrid;
import org.springframework.stereotype.Repository;

@Repository
public interface DutyGridRepository extends BaseRepository<DutyGrid, Long>, DeletableRepository<DutyGrid, Long> {
}
