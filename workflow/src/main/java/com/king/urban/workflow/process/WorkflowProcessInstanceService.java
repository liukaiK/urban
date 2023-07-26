package com.king.urban.workflow.process;

public interface WorkflowProcessInstanceService {

    String startProcessInstanceByKey(String processDefinitionKey, String businessKey);

}
