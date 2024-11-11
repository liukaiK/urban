package main;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class ActivitiTest {

    @Test
    public void test() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("test.xml");

        ProcessEngine processEngine = ProcessEngineConfigurationImpl.createStandaloneInMemProcessEngineConfiguration().buildProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();

        repositoryService.createDeployment().addInputStream("test.bpmn.xml", classPathResource.getInputStream()).deploy();


    }

}
