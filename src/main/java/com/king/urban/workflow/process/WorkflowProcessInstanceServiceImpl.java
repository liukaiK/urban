package com.king.urban.workflow.process;

import org.flowable.engine.IdentityService;
import org.flowable.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class WorkflowProcessInstanceServiceImpl implements WorkflowProcessInstanceService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private IdentityService identityService;

    public String startProcessInstanceByKey(String processDefinitionKey, String businessKey) {
        return runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey).getProcessInstanceId();
    }

    public String startProcessInstanceByKey(String processDefinitionKey, String businessKey, Map<String, Object> variables) {
        return runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables).getProcessInstanceId();
    }

    @Override
    public String startProcessInstanceByKey(String processDefinitionKey, String businessKey, String employeeId, Map<String, Object> variables) {
        identityService.setAuthenticatedUserId(employeeId);
        return runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables).getProcessInstanceId();
    }

    @Override
    public String startProcessInstanceByKey(String processDefinitionKey, String businessKey, String employeeId) {
        identityService.setAuthenticatedUserId(employeeId);
        return runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey).getProcessInstanceId();
    }

}
