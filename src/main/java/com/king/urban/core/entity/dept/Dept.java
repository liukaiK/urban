package com.king.urban.core.entity.dept;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.king.urban.common.constant.SysConstants;
import com.king.urban.common.entity.DeletableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import java.util.Collection;

/**
 * 部门
 *
 * @author liukai
 */
@Getter
@Entity
@DynamicInsert
@DynamicUpdate
@Where(clause = SysConstants.WHERE_DELETE)
@Table(name = "t_sys_dept")
public class Dept extends DeletableEntity<Long> {

    private String name;

    @Convert(converter = Type.Converter.class)
    private Type type;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Dept parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private Collection<Dept> children;

    /**
     * 系统部门
     */
    @Column(nullable = false, columnDefinition = "tinyint unsigned")
    private Boolean systemDept;

    public Dept() {
        this.systemDept = false;
    }

    public Dept(Long deptId) {
        this.id = deptId;
        this.systemDept = false;
    }

    public boolean hasParent() {
        return ObjectUtil.isNotNull(this.parent);
    }

    public boolean hasChildren() {
        return CollectionUtil.isNotEmpty(this.children);
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateParent(Dept parentDept) {
        this.parent = parentDept;
    }

    public void updateType(Type type) {
        this.type = type;
    }

    /**
     * 是否为系统部门
     */
    public boolean isSystemDept() {
        return systemDept;
    }

}
