package com.king.urban.main.event.entity.eventtype;

import com.king.urban.common.constant.SysConstants;
import com.king.urban.common.entity.DeletableEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 案件大类小类
 *
 * @author liukai
 */
@Entity
@Table(name = "t_eve_eventtype")
@org.hibernate.envers.Audited
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
