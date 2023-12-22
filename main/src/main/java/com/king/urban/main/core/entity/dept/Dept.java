package com.king.urban.main.core.entity.dept;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.king.urban.common.constant.SysConstants;
import com.king.urban.common.entity.DeletableEntity;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.util.Collection;

/**
 * 部门
 *
 * @author liukai
 */
@Getter
@Entity
@Audited
@DynamicInsert
@DynamicUpdate
@Where(clause = SysConstants.WHERE_DELETE)
@Table(name = "t_sys_dept")
public class Dept extends DeletableEntity<Long> {

    private String name;

    @Convert(converter = Type.Converter.class)
    private Type type;

    @NotAudited
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

    }

    public Dept(Long deptId) {
        this.id = deptId;
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
