package com.king.urban.core.converter;

import com.king.urban.core.entity.employee.Employee;
import com.king.urban.core.pojo.vo.employee.EmployeeVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeConverter extends BaseConverter<Employee, EmployeeVO> {


    @Mappings({
            @Mapping(target = "deptId", source = "dept.id"),
            @Mapping(target = "deptName", source = "dept.name")
    })
    EmployeeVO convert(Employee employee);

}
