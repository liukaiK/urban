package com.king.urban.test.core.service.post;

import com.king.urban.core.entity.post.Post;
import com.king.urban.core.pojo.dto.post.CreatePostDTO;
import com.king.urban.core.pojo.dto.post.UpdatePostDTO;
import com.king.urban.core.repository.post.PostRepository;
import com.king.urban.core.service.post.PostService;
import com.king.urban.test.core.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

public class PostServiceTest extends BaseTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Test
    public void createPostTest() {
        CreatePostDTO postDTO = new CreatePostDTO();
        postService.create(postDTO);

    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void updatePostTest() {
        Post post = postRepository.getReferenceById(Post.adminId);

        UpdatePostDTO updatePostDTO = new UpdatePostDTO();
        updatePostDTO.setId(post.getId());
        updatePostDTO.setName(post.getName() + "1");
        updatePostDTO.setDeptId(post.getDept().getId());
        updatePostDTO.setMenuIds("1671753902917402624");
        postService.update(updatePostDTO);
    }

}
