package com.king.urban.workflow.service;

import org.flowable.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeployServiceImpl implements DeployService {

    @Autowired
    private RepositoryService repositoryService;

    @Override
    public void deploy(String resourceName, String xml) {
        repositoryService.createDeployment()
                .addString(resourceName, xml)
                .deploy();
    }
}
