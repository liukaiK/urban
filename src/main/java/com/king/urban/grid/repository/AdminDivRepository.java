package com.king.urban.grid.repository;

import com.king.urban.common.repository.BaseRepository;
import com.king.urban.common.repository.DeletableRepository;
import com.king.urban.grid.entity.AdminDiv;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDivRepository extends BaseRepository<AdminDiv, Long>, DeletableRepository<AdminDiv, Long> {
}
