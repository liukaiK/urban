package com.king.urban.event.entity.event;

import com.king.urban.common.constant.SysConstants;
import com.king.urban.common.entity.DeletableEntity;
import com.king.urban.core.entity.employee.Telephone;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * 热线上报的诉求人
 *
 * @author liukai
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Where(clause = SysConstants.WHERE_DELETE)
@Table(name = "t_eve_petitioner")
public class Petitioner extends DeletableEntity<Long> {

    private String name;

    private Telephone mobilePhone;

    @JoinColumn
    @OneToOne(fetch = FetchType.LAZY)
    private Event event;


}
