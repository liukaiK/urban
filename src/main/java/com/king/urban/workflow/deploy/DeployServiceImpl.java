package com.king.urban.workflow.deploy;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeployServiceImpl implements DeployService {

    @Autowired
    private RepositoryService repositoryService;

    public void deploy(String resourceName, String text) {
        repositoryService.createDeployment()
                .addString(resourceName, text).deploy();
    }

    @Override
    public void saveModel(String name) {
        Model model = repositoryService.newModel();
        model.setName(name);
        repositoryService.saveModel(model);
    }

}
