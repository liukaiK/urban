package com.king.urban.core.service.post;

import com.king.urban.core.pojo.dto.post.CreatePostDTO;
import com.king.urban.core.pojo.dto.post.RemovePostDTO;
import com.king.urban.core.pojo.dto.post.SearchPostDTO;
import com.king.urban.core.pojo.dto.post.UpdatePostDTO;
import com.king.urban.core.pojo.vo.post.PostVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    Page<PostVO> search(SearchPostDTO searchPostDTO, Pageable pageable);

    void create(CreatePostDTO createPostDTO);

    void update(UpdatePostDTO updatePostDTO);

    void remove(RemovePostDTO removePostDTO);

}
