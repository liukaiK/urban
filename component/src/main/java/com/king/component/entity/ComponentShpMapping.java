package com.king.component.entity;

import com.king.urban.common.constant.SysConstants;
import com.king.urban.common.entity.DeletableEntity;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 部件shp文件里的属性 和数据库列关系映射表
 *
 * @author liukai
 */
@Getter
@Entity
@DynamicInsert
@DynamicUpdate
@Where(clause = SysConstants.WHERE_DELETE)
@Table(name = "t_com_com_shp_mapping")
public class ComponentShpMapping extends DeletableEntity<Long> {

    /**
     * 数据库列名
     */
    private String columnName;

    /**
     * shp字段属性
     */
    private String fieldName;

    public void updateFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

}
