package com.king.urban.core.service.post;

import com.king.urban.core.converter.PostConverter;
import com.king.urban.core.entity.dept.Dept;
import com.king.urban.core.entity.post.Post;
import com.king.urban.core.pojo.dto.post.CreatePostDTO;
import com.king.urban.core.pojo.vo.employee.PostVO;
import com.king.urban.core.repository.dept.DeptRepository;
import com.king.urban.core.repository.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private PostConverter postConverter;

    @Override
    public Page<PostVO> search(Pageable pageable) {
        Page<Post> page = postRepository.findAll(pageable);
        return postConverter.convertPage(page);
    }

    @Override
    public void create(CreatePostDTO createPostDTO) {
        Dept dept = deptRepository.findById(createPostDTO.getDeptId())
                .orElseThrow(() -> new IllegalArgumentException("部门不存在"));
        Post post = new Post();
        post.updateDept(dept);
        post.updateName(createPostDTO.getName());
        postRepository.save(post);
    }


}
