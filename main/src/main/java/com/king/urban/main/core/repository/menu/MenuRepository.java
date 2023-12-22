package com.king.urban.main.core.repository.menu;

import com.king.urban.common.repository.BaseRepository;
import com.king.urban.common.repository.DeletableRepository;
import com.king.urban.main.core.entity.menu.Menu;
import com.king.urban.main.core.entity.post.Post;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

@Repository
public interface MenuRepository extends BaseRepository<Menu, Long>, DeletableRepository<Menu, Long> {

    Set<Menu> findByPostsIn(Collection<Post> posts);

}
