package com.king.urban.main.grid.repository;

import com.king.urban.common.repository.BaseRepository;
import com.king.urban.common.repository.DeletableRepository;
import com.king.urban.main.grid.entity.AdminDiv;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDivRepository extends BaseRepository<AdminDiv, Long>, DeletableRepository<AdminDiv, Long> {
}
