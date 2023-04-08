package com.king.urban.core.service.post;

import com.king.urban.core.entity.post.Post;
import com.king.urban.core.pojo.dto.post.CreatePostDTO;
import com.king.urban.core.repository.dept.DeptRepository;
import com.king.urban.core.repository.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private DeptRepository deptRepository;

    @Override
    public void create(CreatePostDTO createPostDTO) {
        Post post = new Post();
        deptRepository.findById(createPostDTO.getDeptId()).ifPresent(post::updateDept);
        post.updateName(createPostDTO.getName());
        postRepository.save(post);
    }


}
