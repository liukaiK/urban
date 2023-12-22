package com.king.urban.main.workflow.claim;

import org.flowable.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkflowClaimServiceImpl implements WorkflowClaimService {

    @Autowired
    private TaskService taskService;

    @Override
    public void claim(String taskId, String userId) {
        taskService.claim(taskId, userId);
    }

    @Override
    public void unClaim(String taskId) {
        taskService.unclaim(taskId);
    }

}
