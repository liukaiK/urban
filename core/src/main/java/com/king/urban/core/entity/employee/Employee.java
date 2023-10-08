package com.king.urban.core.entity.employee;

import com.king.urban.common.constant.SysConstants;
import com.king.urban.common.entity.DeletableEntity;
import com.king.urban.core.entity.dept.Dept;
import com.king.urban.core.entity.post.Post;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "t_sys_employee")
@org.hibernate.envers.Audited
@org.hibernate.annotations.DynamicInsert
@org.hibernate.annotations.DynamicUpdate
@org.hibernate.annotations.Where(clause = SysConstants.WHERE_DELETE)
public class Employee extends DeletableEntity<Long> {

    public static final Long adminId = 1L;

    /**
     * 姓名
     */
    private Name name;

    /**
     * 账号
     */
    private Username username;

    private Password password;

    /**
     * 手机号码
     */
    private MobilePhone mobilePhone;

    /**
     * 部门
     */
    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    @org.hibernate.envers.NotAudited
    private Dept dept;

    /**
     * 一个员工可以有多个岗位
     */
    @org.hibernate.envers.NotAudited
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "t_sys_employee_post", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "post_id"))
    private Collection<Post> posts;

    public Employee() {
    }

    public Employee(Long id) {
        this.id = id;
    }

    public void updateUsername(Username username) {
        this.username = username;
    }

    public void updateName(Name name) {
        this.name = name;
    }

    public void updatePassword(Password password) {
        this.password = password;
    }

    public void updateMobilePhone(MobilePhone mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public void updateDept(Dept dept) {
        this.dept = dept;
    }

    /**
     * 获取加密之后的密码
     */
    public String getEncodedPassword() {
        return this.password.getPassword();
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

    public MobilePhone getMobilePhone() {
        return mobilePhone;
    }

    public Dept getDept() {
        return dept;
    }

    public Collection<Post> getPosts() {
        return posts;
    }

}
