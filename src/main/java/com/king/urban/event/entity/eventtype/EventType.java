package com.king.urban.event.entity.eventtype;

import com.king.urban.common.constant.SysConstants;
import com.king.urban.common.entity.DeletableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


/**
 * 案件大类小类
 *
 * @author liukai
 */
@Entity
@Table(name = "t_eve_eventtype")
@org.hibernate.annotations.DynamicInsert
@org.hibernate.annotations.DynamicUpdate
@org.hibernate.annotations.Where(clause = SysConstants.WHERE_DELETE)
public class EventType extends DeletableEntity<Long> {

    private String name;

    /**
     * 代码
     */
    private String code;


}
