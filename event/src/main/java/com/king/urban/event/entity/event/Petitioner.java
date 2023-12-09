package com.king.urban.event.entity.event;

import com.king.urban.common.constant.SysConstants;
import com.king.urban.common.entity.DeletableEntity;

import javax.persistence.*;

/**
 * 热线上报的诉求人
 *
 * @author liukai
 */
@Entity
@Table(name = "t_eve_petitioner")
@org.hibernate.annotations.DynamicInsert
@org.hibernate.annotations.DynamicUpdate
@org.hibernate.annotations.Where(clause = SysConstants.WHERE_DELETE)
public class Petitioner extends DeletableEntity<Long> {

    private String name;

    private String telMobile;

    @JoinColumn
    @OneToOne(fetch = FetchType.LAZY)
    private Event event;


}
