package com.king.urban.core.converter;

import com.king.urban.core.entity.employee.Employee;
import com.king.urban.core.entity.post.Post;
import com.king.urban.core.pojo.vo.employee.EmployeeVO;
import com.king.urban.core.pojo.vo.employee.PostVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeConverter extends BaseConverter<Employee, EmployeeVO> {


    List<EmployeeVO> convert(Page<Employee> page);

    @Mappings({
            @Mapping(target = "deptId", source = "dept.id"),
            @Mapping(target = "deptName", source = "dept.name")
    })
    EmployeeVO convert(Employee employee);

    PostVO convert(Post post);

}
