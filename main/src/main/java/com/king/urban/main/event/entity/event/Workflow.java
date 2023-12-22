package com.king.urban.main.event.entity.event;

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

    public Workflow(String taskId, String taskName, String processInstanceId) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.processInstanceId = processInstanceId;
    }

    protected Workflow() {
    }
}
