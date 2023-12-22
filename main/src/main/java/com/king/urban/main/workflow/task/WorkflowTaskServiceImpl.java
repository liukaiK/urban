package com.king.urban.main.workflow.task;

import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WorkflowTaskServiceImpl implements WorkflowTaskService {

    @Autowired
    private TaskService taskService;

    public List<Task> queryTask(String processInstanceId) {
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
        return tasks;
    }

}
