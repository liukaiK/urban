package com.king.urban.grid.entity;

import com.king.urban.common.constant.SysConstants;
import com.king.urban.common.entity.DeletableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;
import org.springframework.data.geo.Polygon;


/**
 * 单元网格
 *
 * @author liukai
 */
@Getter
@Entity
@DynamicInsert
@DynamicUpdate
@Where(clause = SysConstants.WHERE_DELETE)
@Table(name = "t_sys_cell_grid")
public class CellGrid extends DeletableEntity<Long> {

    /**
     * 单元网格名称
     */
    private String name;

    /**
     * 空间信息
     */
    private Polygon geom;


}
