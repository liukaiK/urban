package com.king.urban.main.core.entity.employee;

import com.king.urban.common.constant.SysConstants;
import com.king.urban.common.entity.DeletableEntity;
import com.king.urban.common.jpa.TelephoneConverter;
import com.king.urban.main.core.entity.dept.Dept;
import com.king.urban.main.core.entity.post.Post;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
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
     * 编号
     */
    private String code;

    /**
     * 生日
     */
    private LocalDate birthday;

    /**
     * 邮箱
     */
    @Getter
    private String email;

    /**
     * 地址
     */
    private String address;

    /**
     * 登录账号
     */
    private Username username;

    private Password password;

    /**
     * 性别
     */
    @Getter
    @Column(length = 1)
    private Character gender;

    /**
     * 监督员标识
     */
    @Column(nullable = false, columnDefinition = "tinyint unsigned")
    private boolean patrol;

    /**
     * 手机号码
     */
    @Getter
    @Convert(converter = TelephoneConverter.class)
    private String telMobile;

    /**
     * 办公室电话号码
     */
    @Getter
    @Convert(converter = TelephoneConverter.class)
    private String telOffice;

    /**
     * 家庭电话号码
     */
    @Getter
    @Convert(converter = TelephoneConverter.class)
    private String telHome;

    /**
     * 部门
     */
    @Getter
    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    @org.hibernate.envers.NotAudited
    private Dept dept;

    /**
     * 一个员工可以有多个岗位
     */
    @Getter
    @org.hibernate.envers.NotAudited
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "t_sys_employee_post", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "post_id"))
    private Collection<Post> posts;

    /**
     * 创建人
     */
    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee createEmployee;

    /**
     * 是否为系统内置，不可通过页面增加系统内置人员
     */
    @Column(nullable = false, columnDefinition = "tinyint unsigned")
    private Boolean systemEmployee;

    public Employee() {
        this.systemEmployee = false;
    }

    public Employee(Long id) {
        this.id = id;
        this.systemEmployee = false;
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

//    public void updateMobilePhone(Telephone mobilePhone) {
//        this.telMobile = mobilePhone;
//    }

    public void updatePatrol(boolean patrol) {
        this.patrol = patrol;
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
        for (Post post : posts) {

        }
        this.posts = posts;
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }

    public void updateGender(char gender) {
        this.gender = gender;
    }

    /**
     * 是否为监督员
     */
    public boolean isPatrol() {
        return patrol;
    }

    /**
     * 是否为系统人员
     */
    public boolean isSystemEmployee() {
        return systemEmployee;
    }

    public void updateTelMobile(String telMobile) {
        this.telMobile = telMobile;
    }

    public void updateCreateEmployee(Employee createEmployee) {
        this.createEmployee = createEmployee;
    }

}
