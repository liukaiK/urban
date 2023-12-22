package com.king.urban.main.core.pojo.dto.post;

import lombok.Data;

@Data
public class CreatePostDTO {

    private String name;

    private Long deptId;

    private String menuIds;

    private String description;

}
