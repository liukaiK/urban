package com.king.urban.core.pojo.dto.employee;

import lombok.Data;

@Data
public class CreateEmployeeDTO {

    private String name;

    private String username;

    private String password;

    private Long deptId;

    private String mobilePhone;

    /**
     * 岗位id 逗号拼接
     */
    private String postIds;

}
