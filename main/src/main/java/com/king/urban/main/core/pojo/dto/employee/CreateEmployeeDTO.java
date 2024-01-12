package com.king.urban.main.core.pojo.dto.employee;

import lombok.Data;

@Data
public class CreateEmployeeDTO {

    private String name;

    private String username;

    private String password;

    private Long deptId;

    private String telMobile;

    /**
     * 办公室电话号码
     */
    private String telOffice;

    /**
     * 家庭电话号码
     */
    private String telHome;

    /**
     * 岗位id 逗号拼接
     */
    private String postIds;

    private boolean system;

}
