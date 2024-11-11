package com.king.urban.workflow.deploy;

public interface DeployService {

    void deploy(String resourceName, String text);

    void saveModel(String name);

}
