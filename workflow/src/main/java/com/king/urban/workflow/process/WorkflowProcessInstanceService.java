package com.king.urban.workflow.process;

import java.util.Map;

public interface WorkflowProcessInstanceService {

    String startProcessInstanceByKey(String processDefinitionKey, String businessKey);

    String startProcessInstanceByKey(String processDefinitionKey, String businessKey, Map<String, Object> variables);

    /**
     * 开启流程
     *
     * @param employeeId 流程发起人
     */
    String startProcessInstanceByKey(String processDefinitionKey, String businessKey, String employeeId, Map<String, Object> variables);

}
