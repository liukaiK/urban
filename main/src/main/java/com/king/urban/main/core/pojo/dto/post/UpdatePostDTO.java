package com.king.urban.main.core.pojo.dto.post;

import lombok.Data;

@Data
public class UpdatePostDTO {

    private Long id;

    private String name;

    private Long deptId;

    private String menuIds;

    private String description;

}
