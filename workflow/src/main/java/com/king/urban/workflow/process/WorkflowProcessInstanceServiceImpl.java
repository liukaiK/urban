package com.king.urban.workflow.process;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkflowProcessInstanceServiceImpl implements WorkflowProcessInstanceService {

    @Autowired
    private RuntimeService runtimeService;

    public String startProcessInstanceByKey(String processDefinitionKey,String businessKey) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey);
        return processInstance.getId();
    }

}
