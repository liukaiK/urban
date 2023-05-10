package com.king.urban.workflow.claim;

public interface WorkflowClaimService {

    /**
     * 签收
     */
    void claim(String taskId, String userId);

    /**
     * 回退
     */
    void unClaim(String taskId);

}
