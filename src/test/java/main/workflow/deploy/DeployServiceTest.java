package main.workflow.deploy;


import com.king.urban.workflow.deploy.DeployService;
import main.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DeployServiceTest extends BaseTest {

    @Autowired
    private DeployService deployService;

    @Test
    @Transactional
    public void deploy() {
        String resourceName = "test.bpmn.xml";
        String text = "text";

        deployService.deploy(resourceName, text);
    }

    @Test
    @Transactional
    public void saveModel() {
        String modelName = "modelName";
        deployService.saveModel(modelName);
    }
}
