package com.king.urban.core.pojo.dto.employee;

import lombok.Data;

@Data
public class UpdateEmployeeDTO {

    private Long id;

    private String name;

    private String username;

    private String gender;

    private Long deptId;

    private String mobilePhone;


}
