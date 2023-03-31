package com.king.urban.core.entity;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class DeletableEntity<ID extends Serializable> extends BaseEntity<ID> {

    /**
     * 数据是否已被删除
     */
    private Boolean deleted;

    private LocalDateTime deletedTime;

}
