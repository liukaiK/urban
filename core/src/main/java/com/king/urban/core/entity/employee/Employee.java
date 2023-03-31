package com.king.urban.core.entity.employee;

import com.king.urban.core.entity.DeletableEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DynamicInsert
@DynamicUpdate
@Where(clause = "deleted = 0")
@Table(name = "t_sys_employee")
public class Employee extends DeletableEntity<Long> {

    /**
     * 姓名
     */
    @Embedded
    private Name name;

    /**
     * 账号
     */
    @Embedded
    private Username username;

    @Embedded
    private Password password;

}
