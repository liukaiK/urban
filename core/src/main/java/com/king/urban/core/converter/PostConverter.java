package com.king.urban.core.converter;

import com.king.urban.common.converter.BaseConverter;
import com.king.urban.core.entity.post.Post;
import com.king.urban.core.pojo.vo.employee.PostVO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostConverter extends BaseConverter<Post, PostVO> {


}
