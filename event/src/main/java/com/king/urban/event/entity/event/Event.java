package com.king.urban.event.entity.event;

import com.king.urban.common.constant.SysConstants;
import com.king.urban.common.entity.DeletableEntity;
import com.king.urban.grid.entity.CellGrid;
import com.king.urban.grid.entity.DutyGrid;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;

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

    private Workflow workflow;

    private Position position;

    protected Event() {
    }

    public Event(Source source) {
        this.source = source;
    }

    public void updatePosition(Position position) {
        this.position = position;
    }

    public void updateCode(String code) {
        this.code = code;
    }

    public Source getSource() {
        return source;
    }

    public String getCode() {
        return code;
    }

    public DutyGrid getDutyGrid() {
        return dutyGrid;
    }

    public CellGrid getCellGrid() {
        return cellGrid;
    }

    public String getCellGridName() {
        return cellGridName;
    }

    public Workflow getWorkflow() {
        return workflow;
    }

    public Position getPosition() {
        return position;
    }

    public void updateWorkflow(Workflow workflow) {
        this.workflow = workflow;
    }

}
