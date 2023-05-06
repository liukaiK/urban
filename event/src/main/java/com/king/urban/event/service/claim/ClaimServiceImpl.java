package com.king.urban.event.service.claim;

import com.king.urban.core.entity.event.Event;
import com.king.urban.workflow.claim.WorkflowClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClaimServiceImpl implements ClaimService {

    @Autowired
    private WorkflowClaimService workflowClaimService;

    @Override
    public void claim(Event event) {

    }

}
