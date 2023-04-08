package com.king.urban.core.entity.post;

import cn.hutool.core.util.ObjectUtil;
import com.king.urban.common.constant.SysConstants;
import com.king.urban.common.entity.DeletableEntity;
import com.king.urban.core.entity.dept.Dept;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;

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

    private String name;

    /**
     * 岗位归属于部门下面
     */
    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Dept dept;

    public void updateName(String name) {
        this.name = name;
    }

    public void updateDept(Dept dept) {
        if (ObjectUtil.isNull(dept)) {
            throw new IllegalArgumentException("dept not be null");
        }
        this.dept = dept;
    }

}
