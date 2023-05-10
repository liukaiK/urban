package com.king.urban.workflow.deploy;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeployServiceImpl implements DeployService {

    @Autowired
    private RepositoryService repositoryService;

    @Override
    public void deploy(String name, String xml) {
        Deployment deploy = repositoryService.createDeployment()
                .addString(name, xml).deploy();
    }

}
