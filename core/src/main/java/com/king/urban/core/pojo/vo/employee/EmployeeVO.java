package com.king.urban.core.pojo.vo.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
public class EmployeeVO {

    private Long id;

    private String name;

    private String username;

    private String deptId;

    private String deptName;

    private Collection<PostVO> posts;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

}
