package com.king.urban.event.entity;

import com.king.urban.common.constant.SysConstants;
import com.king.urban.common.entity.DeletableEntity;
import com.king.urban.grid.entity.CellGrid;
import com.king.urban.grid.entity.DutyGrid;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Entity
@DynamicInsert
@DynamicUpdate
@Where(clause = SysConstants.WHERE_DELETE)
@Table(name = "t_sys_employee")
public class Event extends DeletableEntity<Long> {

    /**
     * 案件编码
     */
    private String code;

    /**
     * 流程实例ID
     */
    private String processInstanceId;

    private DutyGrid dutyGrid;

    private CellGrid cellGrid;

    /**
     * 案件发生地址
     */
    private String address;

}
