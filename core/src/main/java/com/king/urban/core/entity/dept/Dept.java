package com.king.urban.core.entity.dept;

import com.king.urban.common.constant.SysConstants;
import com.king.urban.common.entity.DeletableEntity;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;

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

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Dept parent;


    public Dept() {

    }

    public Dept(Long deptId) {
        this.id = deptId;
    }

    public void updateName(String name) {
        this.name = name;
    }


}
