package com.king.urban.main.core.repository.system;

import com.king.urban.common.repository.BaseRepository;
import com.king.urban.common.repository.DeletableRepository;
import com.king.urban.main.core.entity.system.SystemConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemConfigRepository extends BaseRepository<SystemConfig, Long>, DeletableRepository<SystemConfig, Long> {

    SystemConfig getByName(String name);

}
