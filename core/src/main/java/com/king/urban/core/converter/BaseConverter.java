package com.king.urban.core.converter;

import com.king.urban.common.entity.BaseEntity;

import java.util.Collection;

public interface BaseConverter<E extends BaseEntity, V> {

    V convert(E entity);

    Collection<V> convert(Collection<E> entityList);


}
