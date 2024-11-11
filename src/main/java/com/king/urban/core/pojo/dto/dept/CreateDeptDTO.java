package com.king.urban.core.pojo.dto.dept;

import lombok.Data;

@Data
public class CreateDeptDTO {

    /**
     * 部门名称
     */
    private String name;

    private Long parentId;


}
