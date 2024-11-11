package com.king.urban.core.pojo.vo.employee;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.king.urban.core.pojo.vo.post.PostVO;
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

    private boolean systemEmployee;

    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime createTime;

    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime updateTime;

}
