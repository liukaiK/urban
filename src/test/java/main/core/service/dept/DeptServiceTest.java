package main.core.service.dept;

import com.king.urban.core.pojo.dto.dept.RemoveDeptDTO;
import com.king.urban.core.repository.dept.DeptRepository;
import com.king.urban.core.service.dept.DeptService;
import main.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DeptServiceTest extends BaseTest {

    @Autowired
    private DeptService deptService;
    //
    @Autowired
    private DeptRepository deptRepository;

    @Test
    @Transactional
    public void create() {
//        CreateDeptDTO createDeptDTO = new CreateDeptDTO();
//        String deptName = "部门" + RandomUtil.randomNumbers(10);
//        createDeptDTO.setName(deptName);
//
//        Dept dept = deptService.create(createDeptDTO);
//        Assert.assertNotNull(dept);
//
//        Collection<Dept> depts = deptRepository.findByName(deptName);
//        Assert.assertNotNull(depts);


    }


    @Test
    @Transactional
    public void remove() {
        RemoveDeptDTO removeDeptDTO = new RemoveDeptDTO();

        deptService.remove(removeDeptDTO);
    }
}