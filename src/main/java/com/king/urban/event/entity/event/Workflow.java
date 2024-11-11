package com.king.urban.event.entity.event;

import jakarta.persistence.Embeddable;
import lombok.Getter;



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
