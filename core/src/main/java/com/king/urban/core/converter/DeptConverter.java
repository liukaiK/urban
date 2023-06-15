package com.king.urban.core.converter;

import com.king.urban.core.entity.dept.Dept;
import com.king.urban.core.pojo.vo.dept.DeptVO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeptConverter extends BaseConverter<Dept, DeptVO> {

}
