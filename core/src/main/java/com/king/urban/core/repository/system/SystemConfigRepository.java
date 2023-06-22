package com.king.urban.core.repository.system;

import com.king.urban.common.repository.BaseRepository;
import com.king.urban.common.repository.DeletableRepository;
import com.king.urban.core.entity.system.SystemConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemConfigRepository extends BaseRepository<SystemConfig, Long>, DeletableRepository<SystemConfig, Long> {

}
