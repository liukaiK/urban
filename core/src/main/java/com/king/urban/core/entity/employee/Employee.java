package com.king.urban.core.entity.employee;

import com.king.urban.common.constant.SysConstants;
import com.king.urban.common.entity.DeletableEntity;
import com.king.urban.core.entity.dept.Dept;
import com.king.urban.core.entity.post.Post;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Entity
@DynamicInsert
@DynamicUpdate
@Where(clause = SysConstants.WHERE_DELETE)
@Table(name = "t_sys_employee")
public class Employee extends DeletableEntity<Long> {

    public static final Long adminId = 1L;

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

    /**
     * 手机号码
     */
    private String mobilePhone;

    /**
     * 部门
     */
    @JoinColumn
    @ManyToOne(fetch = FetchType.EAGER)
    private Dept dept;

    /**
     * 一个员工可以有多个岗位
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "t_sys_employee_post", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "post_id"))
    private Collection<Post> posts;

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

    public void updateDept(Dept dept) {
        this.dept = dept;
    }

    /**
     * 获取加密之后的密码
     */
    public String getEncodedPassword() {
        return this.getPassword().getPassword();
    }

    public String getUsername() {
        return this.username.getUsername();
    }

    public String getName() {
        return this.name.getName();
    }

    public void updatePosts(Collection<Post> posts) {
        this.posts = posts;
    }

}
