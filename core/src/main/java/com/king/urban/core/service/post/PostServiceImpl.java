package com.king.urban.core.service.post;

import cn.hutool.core.convert.Convert;
import com.king.urban.core.converter.PostConverter;
import com.king.urban.core.entity.dept.Dept;
import com.king.urban.core.entity.menu.Menu;
import com.king.urban.core.entity.post.Post;
import com.king.urban.core.pojo.dto.post.CreatePostDTO;
import com.king.urban.core.pojo.dto.post.UpdatePostDTO;
import com.king.urban.core.pojo.vo.post.PostVO;
import com.king.urban.core.repository.dept.DeptRepository;
import com.king.urban.core.repository.menu.MenuRepository;
import com.king.urban.core.repository.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private PostConverter postConverter;

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public Page<PostVO> search(Pageable pageable) {
        Page<Post> page = postRepository.findAll(pageable);
        return postConverter.convertPage(page);
    }

    @Override
    public void create(CreatePostDTO createPostDTO) {
        Dept dept = deptRepository.findById(createPostDTO.getDeptId())
                .orElseThrow(() -> new IllegalArgumentException("部门不存在"));

        Long[] menuIds = Convert.toLongArray(createPostDTO.getMenuIds());
        List<Menu> menus = menuRepository.findAllById(Arrays.asList(menuIds));

        Post post = new Post();
        post.updateDept(dept);
        post.updateName(createPostDTO.getName());
        post.updateMenus(menus);
        postRepository.save(post);
    }

    @Override
    public void update(UpdatePostDTO updatePostDTO) {
        Dept dept = deptRepository.findById(updatePostDTO.getDeptId())
                .orElseThrow(() -> new IllegalArgumentException("部门不存在"));

        Long[] menuIds = Convert.toLongArray(updatePostDTO.getMenuIds());
        List<Menu> menus = menuRepository.findAllById(Arrays.asList(menuIds));

        Post post = postRepository.findById(updatePostDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("非法的岗位"));
        post.updateDept(dept);
        post.updateName(updatePostDTO.getName());
        post.updateMenus(menus);
        postRepository.save(post);
    }


}
