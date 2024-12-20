package com.king.urban.common.entity;

import com.king.urban.common.constant.SysConstants;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class DeletableEntity<ID extends Serializable> extends BaseEntity<ID> {

    /**
     * 数据是否已被删除
     */
    @Column(name = SysConstants.DELETED_FILED)
    private Boolean deleted;

    private LocalDateTime deletedTime;

}
