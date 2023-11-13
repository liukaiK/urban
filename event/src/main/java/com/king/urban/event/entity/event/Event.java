package com.king.urban.event.entity.event;

import com.king.urban.common.constant.SysConstants;
import com.king.urban.common.entity.DeletableEntity;
import com.king.urban.core.entity.employee.Employee;
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

    private Workflow workflow;

    /**
     * 疑难案件标识
     */
    @Column(nullable = false, columnDefinition = "tinyint unsigned")
    private Boolean difficult;

    /**
     * 是否在草稿状态
     */
    private boolean draft;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    public Employee employee;

    public String employeeName;


    /**
     * 案件发生位置信息
     */
    private Position position;

    protected Event() {
        this.draft = true;
    }

    public Event(Source source) {
        if (source == Source.GRID_ADMIN) {
            this.draft = true;
        }
        this.source = source;
    }

    public void updatePosition(Position position) {
        this.position = position;
    }

    public void updateCode(String code) {
        this.code = code;
    }

    public void updateWorkflow(Workflow workflow) {
        this.draft = false;
        this.workflow = workflow;
    }

    /**
     * 是否为疑难案件
     */
    public boolean isDifficult() {
        return difficult;
    }

}
