package com.king.urban.grid.entity;

import com.king.urban.common.constant.SysConstants;
import com.king.urban.common.entity.DeletableEntity;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;
import org.locationtech.jts.geom.Polygon;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 行政区划 admin div
 * <p>
 * 行政区划的英文缩写为 "Administrative division"，常用的简写为 "AD" 或 "Admin div"。
 *
 * @author liukai
 */
@Getter
@Entity
@DynamicInsert
@DynamicUpdate
@Where(clause = SysConstants.WHERE_DELETE)
@Table(name = "t_grid_admin_div")
public class AdminDiv extends DeletableEntity<Long> {

    private String name;

    private String code;

    @Convert(converter = Level.Converter.class)
    private Level level;

    private Polygon polygon;

    public void updateName(String name) {
        this.name = name;
    }

    public void updateCode(String code) {
        this.code = code;
    }

    public void updateLevel(Level level) {
        this.level = level;
    }
}
