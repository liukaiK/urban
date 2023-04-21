package com.king.urban.workflow.claim;

import org.flowable.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkflowClaimServiceImpl implements WorkflowClaimService {

    @Autowired
    private TaskService taskService;

}
