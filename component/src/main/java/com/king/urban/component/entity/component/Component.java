package com.king.urban.component.entity.component;

import com.king.urban.common.constant.SysConstants;
import com.king.urban.common.entity.DeletableEntity;
import com.king.urban.grid.entity.CellGrid;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;
import org.locationtech.jts.geom.Point;

import javax.persistence.*;

import static javax.persistence.ConstraintMode.NO_CONSTRAINT;

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

    private String source;

    @JoinColumn(foreignKey = @ForeignKey(NO_CONSTRAINT))
    @ManyToOne(fetch = FetchType.EAGER)
    private CellGrid cellGrid;

    /**
     * 主管部门编码
     */
    private String mainDeptCode;

    /**
     * 主管部门名称
     */
    private String mainDeptName;

    /**
     * 权属部门名称
     */
    private String ownerDeptName;

    /**
     * 权属部门编码
     */
    private String ownerDeptCode;

    /**
     * 养护部门名称
     */
    private String keepDeptName;

    /**
     * 养护部门编码
     */
    private String keepDeptCode;

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

    public void updateCode(String code) {
        this.code = code;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateSource(String source) {
        this.source = source;
    }

    public void updateForm(String form) {
        this.form = form;
    }

    public void updateLocate(String locate) {
        this.locate = locate;
    }

    public void updateMainDeptCode(String mainDeptCode) {
        this.mainDeptCode = mainDeptCode;
    }

    public void updateMainDeptName(String mainDeptName) {
        this.mainDeptName = mainDeptName;
    }

    public void updateOwnerDeptName(String ownerDeptName) {
        this.ownerDeptName = ownerDeptName;
    }

    public void updateOwnerDeptCode(String ownerDeptCode) {
        this.ownerDeptCode = ownerDeptCode;
    }

    public void updateKeepDeptName(String keepDeptName) {
        this.keepDeptName = keepDeptName;
    }

    public void updateKeepDeptCode(String keepDeptCode) {
        this.keepDeptCode = keepDeptCode;
    }


    public void updateState(String state) {
        this.state = state;
    }

    public void updateMaterial(String material) {
        this.material = material;
    }

    public void updatePoint(Point point) {
        this.point = point;
    }
}
