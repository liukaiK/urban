package com.king.component.entity.component;

import com.king.urban.common.constant.SysConstants;
import com.king.urban.common.entity.DeletableEntity;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;
import org.locationtech.jts.geom.Point;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 部件
 *
 * @author liukai
 */
@Getter
@Entity
@DynamicInsert
@DynamicUpdate
@Where(clause = SysConstants.WHERE_DELETE)
@Table(name = "t_com_component")
public class Component extends DeletableEntity<Long> {

    /**
     * 部件编码
     */
    private String code;

    private String name;

    /**
     * 形状
     */
    private String form;

    /**
     * 部件地址
     */
    private String locate;

    /**
     * 部件图片
     */
    private String picUrl;

    /**
     * 坐标
     */
    private Point point;

    /**
     * 状态
     */
    private String state;

    /**
     * 材料
     */
    private String material;

}
