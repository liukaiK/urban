package com.king.urban.core.controller.post;

import com.king.urban.common.Result;
import com.king.urban.core.pojo.dto.post.CreatePostDTO;
import com.king.urban.core.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 岗位
 *
 * @author liukai
 */
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;


    @PostMapping("/create")
    public Result create(@RequestBody CreatePostDTO createPostDTO) {
        postService.create(createPostDTO);
        return Result.success();
    }


}
