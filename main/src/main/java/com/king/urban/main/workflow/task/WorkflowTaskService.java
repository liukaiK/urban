package com.king.urban.main.workflow.task;

import org.flowable.task.api.Task;

import java.util.List;

public interface WorkflowTaskService {

    List<Task> queryTask(String processInstanceId);

}
