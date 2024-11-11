package main.core.service.employee;


import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.king.urban.common.entity.BaseEntity;
import com.king.urban.core.entity.dept.Dept;
import com.king.urban.core.entity.employee.Employee;
import com.king.urban.core.entity.employee.Username;
import com.king.urban.core.entity.post.Post;
import com.king.urban.core.pojo.dto.employee.CreateEmployeeDTO;
import com.king.urban.core.repository.dept.DeptRepository;
import com.king.urban.core.repository.employee.EmployeeRepository;
import com.king.urban.core.repository.post.PostRepository;
import com.king.urban.core.service.employee.EmployeeService;
import main.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmployeeServiceTest extends BaseTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private PostRepository postRepository;


    @Test
    @Transactional
    public void create() {
        login("admin");

        Collection<Dept> depts = deptRepository.findBySystemDept(true);
        Assert.assertNotNull(depts);


        Collection<Post> posts = postRepository.findByDeptInAndSystemPost(Collections.singletonList(depts.iterator().next()), true);
        Assert.assertNotNull(posts);

        String username = RandomUtil.randomString(4);

        CreateEmployeeDTO createEmployeeDTO = new CreateEmployeeDTO();
        createEmployeeDTO.setGender("男");
        createEmployeeDTO.setDeptId(depts.iterator().next().getId());
        createEmployeeDTO.setName(RandomUtil.randomString(2));
        createEmployeeDTO.setUsername(username);
        createEmployeeDTO.setPassword(RandomUtil.randomString(16));
        createEmployeeDTO.setPostIds(StrUtil.join(",", posts.stream().map(BaseEntity::getId).collect(Collectors.toSet())));

        employeeService.create(createEmployeeDTO);

        Employee employee = employeeRepository.getByUsername(new Username(username)).orElseThrow(() -> new IllegalArgumentException(""));

        Assert.assertNotNull(employee);

    }
}