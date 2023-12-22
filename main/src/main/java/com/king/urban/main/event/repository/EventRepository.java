package com.king.urban.main.event.repository;

import com.king.urban.common.repository.BaseRepository;
import com.king.urban.common.repository.DeletableRepository;
import com.king.urban.main.event.entity.event.Event;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends BaseRepository<Event, Long>, DeletableRepository<Event, Long> {
}
