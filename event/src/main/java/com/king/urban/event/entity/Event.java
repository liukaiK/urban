package com.king.urban.event.entity;

import com.king.urban.common.constant.SysConstants;
import com.king.urban.common.entity.DeletableEntity;
import com.king.urban.grid.entity.CellGrid;
import com.king.urban.grid.entity.DutyGrid;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Entity
@DynamicInsert
@DynamicUpdate
@Where(clause = SysConstants.WHERE_DELETE)
@Table(name = "t_eve_event")
public class Event extends DeletableEntity<Long> {

    /**
     * 案件来源
     */
    @Convert(converter = Source.Converter.class)
    private Source source;

    /**
     * 案件编码
     */
    private String code;

    /**
     * 案件发生所在的责任网格
     */
    @JoinColumn
    @ManyToOne(fetch = FetchType.EAGER)
    private DutyGrid dutyGrid;

    @JoinColumn
    @ManyToOne(fetch = FetchType.EAGER)
    private CellGrid cellGrid;

    /**
     * 冗余字段
     */
    private String cellGridName;


    @Embedded
    private Position position;

    private String taskId;

    private String taskName;

    /**
     * 流程实例ID
     */
    private String processInstanceId;

    protected Event() {
    }

    public Event(Source source) {
        this.source = source;
    }

    public void updatePosition(Position position) {
        this.position = position;
    }

}
