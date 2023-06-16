package com.king.urban.event.entity.event;

import lombok.Getter;

import javax.persistence.Embeddable;


@Getter
@Embeddable
public class Workflow {

    private String taskId;

    private String taskName;

    /**
     * 流程实例ID
     */
    private String processInstanceId;

}
