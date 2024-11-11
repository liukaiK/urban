package com.king.urban.core.sys.post;

import com.king.urban.common.Result;
import com.king.urban.core.pojo.dto.post.CreatePostDTO;
import com.king.urban.core.pojo.dto.post.SearchPostDTO;
import com.king.urban.core.pojo.vo.post.PostVO;
import com.king.urban.core.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

/**
 * 岗位
 *
 * @author liukai
 */
@RestController
@RequestMapping("/system/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/search")
//    @SaCheckPermission("system:post:search")
    public Result search(SearchPostDTO searchPostDTO, @PageableDefault Pageable pageable) {
        Page<PostVO> page = postService.search(searchPostDTO, pageable);
        return Result.success(page);
    }

    @PostMapping("/create")
    public Result create(@RequestBody CreatePostDTO createPostDTO) {
        postService.create(createPostDTO);
        return Result.success();
    }


}
