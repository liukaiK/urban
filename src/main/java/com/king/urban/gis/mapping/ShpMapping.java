package com.king.urban.gis.mapping;

import com.king.urban.common.constant.SysConstants;
import com.king.urban.common.entity.DeletableEntity;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import jakarta.persistence.*;
/**
 * shp文件里的属性 和数据库列关系映射表
 *
 * @author liukai
 */
@Getter
@Entity
@DynamicInsert
@DynamicUpdate
@Where(clause = SysConstants.WHERE_DELETE)
@Table(name = "t_gis_shp_mapping")
public class ShpMapping extends DeletableEntity<Long> {

    /**
     * 数据库列名
     */
    private String columnName;

    /**
     * shp字段属性
     */
    private String attributeName;

    /**
     * 类型
     */
    @Convert(converter = Type.Converter.class)
    private Type type;

    public void updateAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

}
