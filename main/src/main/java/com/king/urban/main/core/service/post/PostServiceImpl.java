package com.king.urban.main.core.service.post;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.text.StrFormatter;
import com.king.urban.main.core.converter.PostConverter;
import com.king.urban.main.core.entity.dept.Dept;
import com.king.urban.main.core.entity.employee.Employee;
import com.king.urban.main.core.entity.menu.Menu;
import com.king.urban.main.core.entity.post.Post;
import com.king.urban.main.core.pojo.dto.post.CreatePostDTO;
import com.king.urban.main.core.pojo.dto.post.RemovePostDTO;
import com.king.urban.main.core.pojo.dto.post.SearchPostDTO;
import com.king.urban.main.core.pojo.dto.post.UpdatePostDTO;
import com.king.urban.main.core.pojo.vo.post.PostVO;
import com.king.urban.main.core.repository.dept.DeptRepository;
import com.king.urban.main.core.repository.menu.MenuRepository;
import com.king.urban.main.core.repository.post.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.IdentityService;
import org.flowable.idm.api.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Slf4j
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

    @Autowired
    private IdentityService identityService;

    @Override
    public Page<PostVO> search(SearchPostDTO searchPostDTO, Pageable pageable) {
        Page<Post> page = postRepository.findAll(pageable);
        return postConverter.convertPage(page);
    }

    @Override
    public void create(CreatePostDTO createPostDTO) {

        Dept dept = deptRepository.findById(createPostDTO.getDeptId())
                .orElseThrow(() -> new IllegalArgumentException("部门不存在"));

        if (isExistsByNameInDept(createPostDTO.getName(), dept)) {
            throw new IllegalArgumentException(StrFormatter.format("部门({})下，已经存在({})角色", dept.getName(), createPostDTO.getName()));
        }


        List<Menu> menus = menuRepository.findAllById(Convert.toList(Long.class, createPostDTO.getMenuIds()));

        Post post = new Post();
        post.updateDept(dept);
        post.updateName(createPostDTO.getName());
        post.updateMenus(menus);
        post.updateDescription(createPostDTO.getDescription());
        postRepository.save(post);

        Group group = identityService.newGroup(Convert.convert(String.class, post.getId()));
        group.setName(post.getName());
        identityService.saveGroup(group);

    }

    private boolean isExistsByNameInDept(String name, Dept dept) {
        return postRepository.existsByNameAndDept(name, dept);
    }

    @Override
    public void update(UpdatePostDTO updatePostDTO) {
        Dept dept = deptRepository.findById(updatePostDTO.getDeptId())
                .orElseThrow(() -> new IllegalArgumentException("部门不存在"));

        List<Menu> menus = menuRepository.findAllById(Convert.toList(Long.class, updatePostDTO.getMenuIds()));

        Post post = postRepository.findById(updatePostDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("非法的岗位"));

        post.updateDept(dept);
        post.updateName(updatePostDTO.getName());
        post.updateMenus(menus);
        post.updateDescription(updatePostDTO.getDescription());
        postRepository.save(post);
    }

    @Override
    public void remove(RemovePostDTO removePostDTO) {
        for (Long postId : Convert.toLongArray(removePostDTO.getIds().split(","))) {
            if (Employee.adminId.equals(postId)) {
                throw new IllegalArgumentException("超级管理员不允许删除");
            }
        }

        postRepository.softDeleteAllById(Arrays.asList(Convert.toLongArray(removePostDTO.getIds().split(","))));
    }


}
