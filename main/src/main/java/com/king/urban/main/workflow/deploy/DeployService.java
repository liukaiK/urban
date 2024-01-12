package com.king.urban.main.workflow.deploy;

public interface DeployService {

    void deploy(String resourceName, String text);

    void saveModel(String name);

}
