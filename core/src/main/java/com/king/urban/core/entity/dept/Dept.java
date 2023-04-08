package com.king.urban.core.entity.dept;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.king.urban.common.constant.SysConstants;
import com.king.urban.common.entity.DeletableEntity;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
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

    public final static Long DEFAULT_DEPT_ID = 1L;

    private String name;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Dept parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private Collection<Dept> children;


    public Dept() {

    }

    public Dept(Long deptId) {
        this.id = deptId;
    }

    /**
     * 判断是否为系统内置默认的部门
     */
    public boolean idSystemDept() {
        return DEFAULT_DEPT_ID.equals(this.id);
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


}
