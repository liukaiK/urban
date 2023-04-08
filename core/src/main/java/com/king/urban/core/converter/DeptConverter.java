package com.king.urban.core.converter;

import com.king.urban.core.entity.dept.Dept;
import com.king.urban.core.pojo.vo.dept.DeptVO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeptConverter extends BaseConverter<Dept, DeptVO> {

    List<DeptVO> convert(Page<Dept> page);

}
