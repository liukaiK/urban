package com.king.urban.common.repository;

import com.king.urban.common.constant.SysConstants;
import com.king.urban.common.entity.DeletableEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@NoRepositoryBean
public interface DeletableRepository<T extends DeletableEntity, ID> {

//    @Transactional
//    @Modifying
//    @Query("update #{#entityName} set " + SysConstants.DELETED_FILED + " = id, deletedTime = :#{T(java.time.LocalDateTime).now()} where id = :id and " + SysConstants.WHERE_DELETE)
//    void softDeleteById(@Param("id") ID id);
//
//    @Transactional
//    @Modifying
//    @Query("update #{#entityName} set " + SysConstants.DELETED_FILED + " = id, deletedTime = :#{T(java.time.LocalDateTime).now()} where id in :ids and " + SysConstants.WHERE_DELETE)
//    void softDeleteAllById(@Param("ids") Collection<? extends ID> ids);

}
