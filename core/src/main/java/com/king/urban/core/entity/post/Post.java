package com.king.urban.core.entity.post;

import cn.hutool.core.util.ObjectUtil;
import com.king.urban.common.constant.SysConstants;
import com.king.urban.common.entity.DeletableEntity;
import com.king.urban.core.entity.dept.Dept;
import com.king.urban.core.entity.employee.Employee;
import com.king.urban.core.entity.menu.Menu;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Collection;

/**
 * 岗位
 *
 * @author liukai
 */
@Getter
@Entity
@DynamicInsert
@DynamicUpdate
@Where(clause = SysConstants.WHERE_DELETE)
@Table(name = "t_sys_post")
public class Post extends DeletableEntity<Long> {

    public static final Long adminId = 1L;

    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "t_sys_employee_post", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private Collection<Employee> employees;

    /**
     * 岗位归属于部门下面
     */
    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Dept dept;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "t_sys_post_menu", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "menu_id"))
    private Collection<Menu> menus;

    public void updateName(String name) {
        this.name = name;
    }

    public void updateDept(Dept dept) {
        if (ObjectUtil.isNull(dept)) {
            throw new IllegalArgumentException("dept not be null");
        }
        this.dept = dept;
    }

    public void updateMenus(Collection<Menu> menus) {
        this.menus = menus;
    }

}
