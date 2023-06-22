package com.king.urban.core.entity.menu;

import com.king.urban.common.constant.SysConstants;
import com.king.urban.common.entity.DeletableEntity;
import com.king.urban.core.entity.post.Post;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Getter
@Entity
@DynamicInsert
@DynamicUpdate
@Where(clause = SysConstants.WHERE_DELETE)
@Table(name = "t_sys_menu")
public class Menu extends DeletableEntity<Long> {


    private String name;

    /**
     * 权限标识
     */
    private String permission;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "t_sys_post_menu", joinColumns = @JoinColumn(name = "menu_id"), inverseJoinColumns = @JoinColumn(name = "post_id"))
    private Collection<Post> posts;

    public void updateName(String name) {
        this.name = name;
    }

    public void updatePermission(String permission) {
        this.permission = permission;
    }

    public void updatePosts(Collection<Post> posts) {
        this.posts = posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(permission, menu.permission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permission);
    }

}
