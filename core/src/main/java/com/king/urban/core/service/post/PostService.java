package com.king.urban.core.service.post;

import com.king.urban.core.pojo.dto.post.CreatePostDTO;
import com.king.urban.core.pojo.vo.employee.PostVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    Page<PostVO> search(Pageable pageable);

    void create(CreatePostDTO createPostDTO);

}
