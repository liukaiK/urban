package com.king.urban.grid.repository;

import com.king.urban.common.repository.BaseRepository;
import com.king.urban.common.repository.DeletableRepository;
import com.king.urban.grid.entity.CellGrid;
import org.springframework.stereotype.Repository;

@Repository
public interface CellGridRepository extends BaseRepository<CellGrid, Long>, DeletableRepository<CellGrid, Long> {
}
