package com.king.urban.main.core.converter;

import com.king.urban.common.converter.BaseConverter;
import com.king.urban.main.core.entity.post.Post;
import com.king.urban.main.core.pojo.vo.post.PostVO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostConverter extends BaseConverter<Post, PostVO> {


}
