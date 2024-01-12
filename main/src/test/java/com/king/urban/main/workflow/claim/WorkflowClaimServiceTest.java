package com.king.urban.main.workflow.claim;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WorkflowClaimServiceTest {

    @Autowired
    private WorkflowClaimService workflowClaimService;

    @Test
    public void claim() {
//        workflowClaimService.claim(null, null);
    }

    @Test
    public void unClaim() {
    }

}