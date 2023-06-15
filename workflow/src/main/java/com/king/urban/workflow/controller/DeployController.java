package com.king.urban.workflow.controller;

import com.king.urban.workflow.service.DeployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workflow")
public class DeployController {

    @Autowired
    private DeployService deployService;

    /**
     * 流程部署
     */
    @PostMapping("/deploy")
    public void deploy(String resourceName, String xml) {
        deployService.deploy(resourceName, xml);
    }


}
