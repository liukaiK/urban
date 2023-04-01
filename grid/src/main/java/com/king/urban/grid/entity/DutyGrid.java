package com.king.urban.grid.entity;

import com.king.urban.common.constant.SysConstants;
import com.king.urban.common.entity.DeletableEntity;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Collection;

/**
 * 责任网格
 *
 * @author liukai
 */
@Getter
@Entity
@DynamicInsert
@DynamicUpdate
@Where(clause = SysConstants.WHERE_DELETE)
@Table(name = "t_sys_duty_grid")
public class DutyGrid extends DeletableEntity<Long> {

    /**
     * 责任网格名称
     */
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "t_sys_duty_cell_grid", joinColumns = @JoinColumn(name = "duty_grid_id"), inverseJoinColumns = @JoinColumn(name = "cell_grid_id"))
    private Collection<CellGrid> cellGrids;

}
