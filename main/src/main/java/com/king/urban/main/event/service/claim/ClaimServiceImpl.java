package com.king.urban.main.event.service.claim;

import com.king.urban.main.core.entity.employee.Employee;
import com.king.urban.main.event.entity.event.Event;
import com.king.urban.main.workflow.claim.WorkflowClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClaimServiceImpl implements ClaimService {

    @Autowired
    private WorkflowClaimService workflowClaimService;

    @Override
    public void claim(Event event, Employee employee) {

        workflowClaimService.claim(event.getWorkflow().getTaskId(), String.valueOf(employee.getId()));
    }

}
