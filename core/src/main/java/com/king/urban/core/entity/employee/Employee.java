package com.king.urban.core.entity.employee;

import com.king.urban.common.constant.SysConstants;
import com.king.urban.common.entity.DeletableEntity;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Entity
@DynamicInsert
@DynamicUpdate
@Where(clause = SysConstants.WHERE_DELETE)
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

    private String mobilePhone;

    public void updateUsername(Username username) {
        this.username = username;
    }

    public void updateName(Name name) {
        this.name = name;
    }

    public void updatePassword(Password password) {
        this.password = password;
    }

    public void updateMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }



}
